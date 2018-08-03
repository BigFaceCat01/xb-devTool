package xb.dev.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.dev.tools.constant.QueueConstant;
import xb.dev.tools.dao.entity.Comment;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.model.NewsModel;
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
public class DevToolsApplicationTests {
//    @Autowired
    private MongoNewsService newsService;
//    @Autowired
    private MybatisNewsService xbNewsService;
//    @Autowired
    private RedisNewsService redisXbNewsService;
    @Autowired
    private MessageService messageService;

//    @Test
    public void testInsertNews(){
        NewsEntity newsModel = new NewsEntity();
        newsModel.setAuthor("zhang san");
        newsModel.setBody("this is a test body");
        newsModel.setBrowseCount(8245L);
        newsModel.setCreateTime(new Date());
        newsModel.setOpposeCount(50L);
        newsModel.setSource("CCTV");
        newsModel.setTitle("this is a test title");
        newsModel.setType("history,culture,life");
        newsModel.setStatus((byte)1);
        newsModel.setSupportCount(3025L);
        Comment comment = new Comment();
        comment.setStatus((byte)1);
        comment.setCommentTime(new Date());
        comment.setContent("this is a comment");
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        newsModel.setComments(commentList);
        try {
            newsService.insertNews(newsModel);
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }
//    @Test
    public void testInsertXbNews(){
        NewsEntity newsModel = new NewsEntity();
        String s =new SimpleDateFormat("yyyyMMdd").format(new Date());
        newsModel.setNewsId("XB"+s+"00005");
        newsModel.setAuthor("zhao si");
        newsModel.setBody("body create by zhao si");
        newsModel.setBrowseCount(89245L);
        newsModel.setCreateTime(new Date());
        newsModel.setOpposeCount(90L);
        newsModel.setSource("BILI BILI");
        newsModel.setTitle("title create by zhao si");
        newsModel.setType("math,computer");
        newsModel.setStatus((byte)1);
        newsModel.setSupportCount(7568L);
        newsModel.setDeleteFlag(false);
        try {
            xbNewsService.insertNews(newsModel);
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }
//    @Test
    public void deleteNews(){
        try {
            xbNewsService.deleteNews("XB2018080200005");
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void testInsertXbNewsRedis(){
        NewsEntity newsModel = new NewsEntity();
        String s =new SimpleDateFormat("yyyyMMdd").format(new Date());
        newsModel.setNewsId("XB"+s+"00005");
        newsModel.setAuthor("zhao si");
        newsModel.setBody("body create by zhao si");
        newsModel.setBrowseCount(89245L);
        newsModel.setCreateTime(new Date());
        newsModel.setOpposeCount(90L);
        newsModel.setSource("BILI BILI");
        newsModel.setTitle("title create by zhao si");
        newsModel.setType("math,computer");
        newsModel.setStatus((byte)1);
        newsModel.setSupportCount(7568L);
        newsModel.setDeleteFlag(false);
        try {
            redisXbNewsService.insertNews(newsModel);
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }
//    @Test
    public void testQuery(){
        try {
            List<NewsEntity> l = redisXbNewsService.queryAll();
            System.out.println();
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testRabbitMq(){
        try {
            messageService.sendMessage("hello world",QueueConstant.HELLO_QUEUE);
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }

}
