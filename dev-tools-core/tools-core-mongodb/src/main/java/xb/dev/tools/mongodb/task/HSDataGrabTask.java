package xb.dev.tools.mongodb.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import xb.dev.tools.mongodb.model.HSCodeCatagoryModel;
import xb.dev.tools.mongodb.model.HSRequestFaildModel;
import xb.dev.tools.mongodb.util.HttpUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Created by huangxb
 * @date 2018-10-26 16:54:15
 */
@Slf4j
@Component("hSDataGrabTask")
public class HSDataGrabTask implements Runnable {

    private AtomicLong total ;

    private MongoTemplate mongoTemplate;

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void setTotal(AtomicLong total) {
        this.total = total;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private HSDocumentSaveTask hSDocumentSaveTask;

    private int page;

    private Thread dataSaveTask;
    /**
     * 存储数据队列
     */
    private ArrayBlockingQueue<HSCodeCatagoryModel> dataQueue;

    public HSDataGrabTask() {
    }

    public HSDataGrabTask(ArrayBlockingQueue<HSCodeCatagoryModel> dataQueue, Thread dataSaveTask, int page, HSDocumentSaveTask hsDocumentSaveTask, AtomicLong total) {
        this.dataQueue = dataQueue;
        this.dataSaveTask = dataSaveTask;
        this.page = page;
        this.hSDocumentSaveTask = hsDocumentSaveTask;
        this.total = total;
    }

    public HSDataGrabTask( int page, AtomicLong total,MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.page = page;
        this.total = total;
    }

    @Override
    public void run() {
        List<HSRequestFaildModel> dead = new ArrayList<>();
        //定义请求前缀
        String base = "https://www.hsbianma.com/Search/";
        //定义请求参数
        String filterParam = "filterFailureCode=true";
        //定义请求头
        Map<String,String> headers = new HashMap<>(16);
        headers.put("Accept","*/*");
        headers.put("Accept-Language","zh-CN,zh;q=0.9");
        headers.put("Cache-Control","max-age");
        String[] ua1 = {"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1"
                        ,"Mozilla/5.0 (Windows NT 6.1; WOW64; rv:6.0) Gecko/20100101 Firefox/6.0",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50",
                        "Opera/9.80 (Windows NT 6.1; U; zh-cn) Presto/2.9.168 Version/11.50",
                                "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 2.0.50727; SLCC2; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; InfoPath.3; .NET4.0C; Tablet PC 2.0; .NET4.0E)"};
         headers.put("Connection","keep-alive");
        headers.put("Referer","http://www.baidu.com");
        //获得待查询词组列表
        JSONArray jsonArray ;
        try (InputStream is = new FileInputStream("D:/hs-catagory-02.json")) {
            jsonArray = JSON.parseObject(is,null);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        log.info("**********************数据抓取开始****************************");
        jsonArray.forEach(item->{
            JSONObject obj = (JSONObject) item;
            boolean flag = true;
            for (int i =1;i<=10;i++) {
                if(page==1){
                    if(i%2==0){
                        continue;
                    }
                }else {
                    if(i%2!=0){
                        continue;
                    }
                }

                String url = base + i + "?" + filterParam + "&keywords=" + obj.getString("catagoryName");
                try {
                    //休眠2秒继续请求
                    Thread.sleep(500);
                    long in = System.currentTimeMillis() % 5;
                    headers.put("User-Agent", ua1[(int) in]);
                    String html = HttpUtil.getTextContentFromUrl(url, "utf-8", headers);
                    if (html == null) {
                        log.warn("请求路径{}返回结果为null", url);
                        dead.add(new HSRequestFaildModel(obj.getString("catagoryName"), obj.getString("number"), url));
//                        break;
                    }
                    //将请求结果组装为实体，并存放到队列中
                    flag = putDataToQueue(html, obj);
                } catch (Exception e) {
                    //如果请求返回html文档解析失败,记录日志，继续向下请求
                    log.error("解析{}返回结果失败", url);
//                    continue;
                }
            }

        });
//        //当数据爬取结束，通过检测队列是否为空，来结束数据保存任务线程的结束
//        boolean empty =dataQueue.isEmpty();
//        while(!empty) {
//            try {
//                Thread.sleep(100);
//                empty = dataQueue.isEmpty();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        //生成excel
//        if(page % 2 == 0) {
//            //队列已为空，则打断数据保存线程的任务
//            dataSaveTask.interrupt();
//            log.info("抓取总数为{}",total.get());
//            new Thread(hSDocumentSaveTask).start();
//        }
        if(!dead.isEmpty()){
            deadDataHandler(dead,headers,ua1);
        }
    }

    private void deadDataHandler(List<HSRequestFaildModel> dead,Map<String,String> headers,String[] ua1) {
        List<HSRequestFaildModel> de = new ArrayList<>();
        dead.forEach(item->{
            try {
                //休眠2秒继续请求
                Thread.sleep(500);
                long in = System.currentTimeMillis()%5;
                headers.put("User-Agent",ua1[(int)in]);
                log.info("重新请求{}",item.getUrl());
                String html = HttpUtil.getTextContentFromUrl(item.getUrl(),"utf-8",headers);
                if(html==null){
                    log.warn("请求路径{}返回结果为null",item);
                    dead.add(item);
//                        break;
                }
                String s= JSON.toJSONString(item);
                JSONObject obj = JSONObject.parseObject(s);
                //将请求结果组装为实体，并存放到队列中
                putDataToQueue(html,obj);
            } catch (Exception e) {
                //如果请求返回html文档解析失败,记录日志，继续向下请求
                log.error("解析{}返回结果失败",item.getUrl());
//                    continue;
            }
        });
        if(!de.isEmpty()){
            deadDataHandler(de,headers,ua1);
        }
    }

    private boolean putDataToQueue(String html,JSONObject obj) throws Exception{
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("w105");
        //如果未查询到节点，表示翻页已到最后,进行下一词组查询
        if(elements.isEmpty()){
            return false;
        }
        Set<HSCodeCatagoryModel> r = new HashSet<>(40);
        elements.forEach(aTag->{
            if("商品编码".equals(aTag.text())){
                return;
            }
            HSCodeCatagoryModel model = new HSCodeCatagoryModel();
            model.setHsCode(aTag.text());
            model.setCatatoryName(obj.getString("catagoryName"));
            model.setNumber(obj.getString("number"));
            r.add(model);


        });
        try {
            //保存
            log.info("抓取{}条",r.size());
//            mongoTemplate.insert(r, "test-code");
            mongoTemplate.insert(r, "hsCode");
        }catch (Exception e){
            log.error("保存失败{}"+JSON.toJSONString(r));
        }
        return true;
    }
}
