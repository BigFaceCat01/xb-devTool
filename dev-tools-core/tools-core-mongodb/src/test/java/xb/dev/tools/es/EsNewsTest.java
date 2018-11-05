package xb.dev.tools.es;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.dev.document.DocumentUtil;
import xb.dev.document.model.HSCatagoryModel;
import xb.dev.tools.api.es.entity.EsNewsEntity;
import xb.dev.tools.api.es.provider.EsNewsClient;
import xb.dev.tools.common.Result;
import xb.dev.tools.mongodb.MongodbApplication;
import xb.dev.tools.mongodb.model.HSCodeCatagoryModel;
import xb.dev.tools.mongodb.task.HSDataGrabTask;
import xb.dev.tools.mongodb.task.HSDataSaveTask;

import javax.validation.constraints.NotNull;

/**
 * @author Created by huangxb
 * @date 2018-10-24 10:07:58
 */
@SpringBootTest(classes = MongodbApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class EsNewsTest {
    @Autowired
    private HSDataSaveTask hsDataSaveTask;
    @Autowired
    private EsNewsClient esNewsClient;
    @Test
    public void testFeign(){
        Result<List<EsNewsEntity>> a = esNewsClient.listBy("中国",null,null);
        System.out.println();
    }
    @Test
    public void downHSCode(){
        //从excel表格中获得待查询词组列表
//        buildWordsFromExcel("D:/hs-catagory-02.xlsx");
        //读取词组，爬取网页结果
        ArrayBlockingQueue<HSCodeCatagoryModel> dataQueue = new ArrayBlockingQueue<>(100);
        hsDataSaveTask.setDataQueue(dataQueue);
        Thread save = new Thread(hsDataSaveTask);
//        Thread grab = new Thread(new HSDataGrabTask(dataQueue,save));
        save.start();
//        grab.start();
        //生成excel
    }

    public static void main(String[] args){
        buildWordsFromExcel("D:/hs-catagory-02.xlsx");
    }

    /**
     * 从excel表格中获得待查询词组列表,返回json文件，存储位置与表格路径同级，名称同表格名称，后缀.json
     * 如  D:/xxx.xls -> D:/xxx.json
     * @param excelPath 表格路径
     */
    public static void buildWordsFromExcel(@NotNull String excelPath){
        if(excelPath==null){
            return;
        }
        try {
            //读取excel
            Map<String,List<HSCatagoryModel>> result =  DocumentUtil.getDataByPath(HSCatagoryModel.class,excelPath);
            //获得excel解析结果
            List<HSCatagoryModel> models = result.get("DOCUMENT");
            List<HSCatagoryModel> m = new ArrayList<>(120);
            int len = models.size();
            int count = 1;
                for(int i = 0;i<len;i++){
                    m.add(models.get(i));
                    if(i ==0){
                        continue;
                    }
                    if(i % 100 == 0||i==(len-1)){
                        //生成json结果文件路径
                        String out = "D:/hsCode/json/category-"+count+".json";
                        count++;
                        //json数据流写到文件
                        try (OutputStream os = new FileOutputStream(out)) {
                            JSON.writeJSONString(os,m);
                            m.clear();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }

            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
