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
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DevToolsApplication.class)
@EnableAutoConfiguration
public class DevToolsApplicationTests {
    @Autowired
    private NewsService newsService;

    @Test
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
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        newsModel.setComments(commentList);
        try {
            newsService.insertNews(newsModel);
        } catch (XbServiceException e) {
            e.printStackTrace();
        }
    }

}
