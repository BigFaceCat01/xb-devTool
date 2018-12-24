package xb.dev.tools.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.dev.document.DocumentUtil;
import xb.dev.document.exception.DocumentHandlerException;
import xb.dev.tools.es.model.HSCodeModel;

import java.util.List;
import java.util.Map;

/**
 * @author Created by huangxb
 * @date 2018-10-25 09:27:10
 */
@SpringBootTest(classes = EsApplication.class)
@RunWith(SpringRunner.class)
public class EsTest {
    @Test
    public void test(){
        try {
            Map<String,List<HSCodeModel>> result =  DocumentUtil.getDataByPath(HSCodeModel.class,"D:/HSCode.xls");
            System.out.println();
        } catch (DocumentHandlerException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void hsCodeTest(){

    }
}
