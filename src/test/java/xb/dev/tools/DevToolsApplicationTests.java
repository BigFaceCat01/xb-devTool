package xb.dev.tools;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.mongo.dao.entity.Comment;
import xb.dev.tools.mongo.model.NewsModel;
import xb.dev.tools.mongo.service.NewsService;
import xb.dev.tools.mybatis.dao.entity.XbNewsEntity;
import xb.dev.tools.mybatis.service.XbNewsService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevToolsApplication.class)
@EnableAutoConfiguration
public class DevToolsApplicationTests {
//    @Autowired
    private NewsService newsService;
    @Autowired
    private XbNewsService xbNewsService;

//    @Test
    public void testInsertNews(){
        NewsModel newsModel = new NewsModel();
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
    @Test
    public void testInsertXbNews(){
        XbNewsEntity newsModel = new XbNewsEntity();
        String s =new SimpleDateFormat("yyyyMMdd").format(new Date());
        newsModel.setNewsId("XB"+s+"00001");
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
        try {
            xbNewsService.insertNews(newsModel);
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }

}
