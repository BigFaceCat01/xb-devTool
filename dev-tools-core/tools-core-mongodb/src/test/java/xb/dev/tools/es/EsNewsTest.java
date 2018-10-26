package xb.dev.tools.es;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

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

/**
 * @author Created by huangxb
 * @date 2018-10-24 10:07:58
 */
@SpringBootTest(classes = MongodbApplication.class)
@RunWith(SpringRunner.class)
@EnableAutoConfiguration
public class EsNewsTest {
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
        buildWordsFromExcel("D:/hs-catagory-02.xlsx");
        //读取词组，爬取网页结果

        //生成excel
    }

    /**
     * 从excel表格中获得待查询词组列表,返回json文件，存储位置与表格路径同级，名称同表格名称，后缀.json
     * 如  D:/xxx.xls -> D:/xxx.json
     * @param excelPath 表格路径
     */
    public void buildWordsFromExcel(String excelPath){
        if(excelPath==null){

        }
        try {
            //读取excel
            Map<String,List<HSCatagoryModel>> result =  DocumentUtil.getDataByPath(HSCatagoryModel.class,"");
            //获得excel解析结果
            List<HSCatagoryModel> models = result.get("DOCUMENT");
            //生成json结果文件路径
            String out = excelPath.substring(0,excelPath.lastIndexOf("."))+".json";
            //json数据流写到文件
            try (OutputStream os = new FileOutputStream(out)) {
                JSON.writeJSONString(os,models);
            }
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
