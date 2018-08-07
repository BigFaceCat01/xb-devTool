package xb.dev.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.redis.service.RedisNewsService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevToolsApplication.class)
@EnableAutoConfiguration
public class RedisProviderTest {
    @Autowired
    private RedisNewsService redisNewsService;


//    @Test
    public void testInsertNews(){
        NewsEntity newsEntity = SomeTest.buildWithComment();
        try {
            redisNewsService.insertNews(newsEntity);
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }
//    @Test
    public void testQueryOne(){
        try {

            NewsEntity l = redisNewsService.queryOne("XB2018080400006");
            System.out.println(l);
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertTimeout(){




                NewsEntity newsEntity = SomeTest.buildWithComment();
                try {
                    redisNewsService.insertNewsWithTimeout(newsEntity,"XB2018080400006",1);

                } catch (XbServiceException e) {
                    e.printStackTrace();
                }


    }

}
