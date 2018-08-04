package xb.dev.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.dev.tools.constant.RabbitConstant;
import xb.dev.tools.dao.entity.Comment;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.mongo.service.MongoNewsService;
import xb.dev.tools.tool.mybatis.service.MybatisNewsService;
import xb.dev.tools.tool.rabbit.service.MessageService;
import xb.dev.tools.tool.redis.service.RedisNewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevToolsApplication.class)
@EnableAutoConfiguration
public class MongoProviderTest {

}
