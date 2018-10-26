package xb.dev.tools.mongodb.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import xb.dev.tools.mongodb.model.HSCodeCatagoryModel;
import xb.dev.tools.mongodb.util.HttpUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Created by huangxb
 * @date 2018-10-26 16:54:15
 */
@Slf4j
public class HSDataGrabTask implements Runnable {
    private Thread dataSaveTask;
    /**
     * 存储数据队列
     */
    private ArrayBlockingQueue<HSCodeCatagoryModel> dataQueue;
    @Override
    public void run() {
        //定义请求前缀
        String base = "https://www.hsbianma.com/Search/";
        //定义请求参数
        String filterParam = "filterFailureCode=true";
        //获得待查询词组列表
        JSONArray jsonArray ;
        try (InputStream is = new FileInputStream("D:/hs-catagory-02.json")) {
            jsonArray = JSON.parseObject(is,null);
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        jsonArray.forEach(item->{
            JSONObject obj = (JSONObject) item;
            for(int i=0;;i++){
                String url = base + i +"?" + filterParam + "&keywords=" + obj.getString("catagoryName");
                String html = HttpUtil.getTextContentFromUrl(url,"utf-8",null);
                Document document = Jsoup.parse(html);
                Elements elements = document.getElementsByClass("w105");
                elements.forEach(aTag->{
                    Element hsCode = aTag.firstElementSibling();
                    if(hsCode!=null) {
                        HSCodeCatagoryModel model = new HSCodeCatagoryModel();
                        model.setHsCode(hsCode.text());
                        model.setCatatoryName(obj.getString("catagoryName"));
                        model.setNumber(obj.getString("number"));
                        try {
                            dataQueue.put(model);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

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
}
