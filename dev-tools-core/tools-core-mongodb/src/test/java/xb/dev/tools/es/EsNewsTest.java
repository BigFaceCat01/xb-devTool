package xb.dev.tools.es;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
}
