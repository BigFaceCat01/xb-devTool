package xb.dev.tools.es.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import xb.dev.tools.es.constant.News163Contants;
import xb.dev.tools.es.dao.entity.EsNewsEntity;
import xb.dev.tools.es.util.HttpUtil;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Created by huangxb
 * @date 2018-10-23 18:05:54
 */
@Slf4j
public class DataGrabTask implements Runnable{
    private Thread dataSaveTask;

    /**
     * 存储数据队列
     */
    private ArrayBlockingQueue<EsNewsEntity> dataQueue;

    public DataGrabTask(ArrayBlockingQueue<EsNewsEntity> dataQueue,Thread dataSaveTask){
        this.dataQueue = dataQueue;
        this.dataSaveTask = dataSaveTask;
    }

    @Override
    public void run() {
        syncNews163com();
    }

    private void syncNews163com(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss",Locale.SIMPLIFIED_CHINESE);
        Calendar calendar = Calendar.getInstance();
        //定义请求前缀
        String s1 = News163Contants.NEWS_163_SITE + "/special/00804KVA/";
        //定义请求后缀
        String s2 = ".js?callback=data_callback%20HTTP/1.1";
        Class cls = News163Contants.class;
        //得到常量表中常量
        Field[] navs = cls.getFields();
        for(Field f:navs){
            //如果常量不是以CM开始，则表示该变量不是导航常量
            if(!f.getName().startsWith("CM")) {
                continue;
            }
            //用于翻页
            for(int i=0;;i++){
                String result = "";
                //第一页不需要添加页数后缀
                if(i==0) {
                    String url = s1+f.getName().toLowerCase()+s2;
                    result = HttpUtil.getTextContentFromUrl(url, "gbk", null);
                }else {
                    //其他页需要添加页数后缀，即xxx_02.xxx_03等
                    String url = s1+f.getName().toLowerCase()+"_0"+(i+1)+s2;
                    result = HttpUtil.getTextContentFromUrl(url, "gbk", null);
                }
                //如果请求结果为空，则进入下一导航页
                if(result==null||result.trim().equals("")){
                    break;
                }
                //解析结果
                String json = result.substring(0,result.length()-1).substring(result.indexOf("["));
                JSONArray jsonArray = JSON.parseArray(json);
                int size = jsonArray.size();
                for(int j=0;j<size;j++){
                    try {
                        EsNewsEntity e = buildEntity(jsonArray.getJSONObject(j),f,formatter,calendar);
                        dataQueue.put(e);
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.warn(e.getMessage()+"jsonObject:{}",jsonArray.getJSONObject(j).toJSONString());
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        int s =dataQueue.size();
        while(s>0) {
            try {
                Thread.sleep(100);
                s = dataQueue.size();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        dataSaveTask.interrupt();

    }

    private List<String> handleKeyWords(JSONArray jsonArray){
        int size = jsonArray.size();
        List<String> r = new ArrayList<>(6);
        for(int j=0;j<size;j++){
            JSONObject obj = jsonArray.getJSONObject(j);
            r.add(obj.getString("keyname"));
        }
        return r;
    }

    private EsNewsEntity buildEntity(JSONObject obj,Field f,DateTimeFormatter formatter,Calendar calendar) throws Exception{
        EsNewsEntity esNewsEntity = new EsNewsEntity();
        esNewsEntity.setTitle(obj.getString("title"));
        esNewsEntity.setAuthor("news.163.com");
        if(!"".equals(obj.getString("time").trim())){
            LocalDateTime t = LocalDateTime.parse(obj.getString("time"),formatter);
            calendar.set(t.getYear(),t.getMonthValue(),t.getDayOfMonth(),t.getHour(),t.getMinute(),t.getSecond());
            esNewsEntity.setCreateTime(calendar.getTime());
        }
        esNewsEntity.setBrowseCount(obj.getLong("tienum"));
        esNewsEntity.setSupportCount(0L);
        esNewsEntity.setOpposeCount(0L);
        esNewsEntity.setSource("news.163.com");
        esNewsEntity.setType(f.get(null).toString());
        esNewsEntity.setImg(obj.getString("imgurl"));
        esNewsEntity.setKeywords(handleKeyWords(obj.getJSONArray("keywords")));
        return esNewsEntity;
    }
}
