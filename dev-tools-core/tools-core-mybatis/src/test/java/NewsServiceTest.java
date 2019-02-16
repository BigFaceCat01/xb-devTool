import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import xb.dev.tools.mybatis.MybatisApplication;
import xb.dev.tools.mybatis.dao.entity.Comment;
import xb.dev.tools.mybatis.dao.entity.NewsEntity;
import xb.dev.tools.mybatis.dao.entity.UserEntity;
import xb.dev.tools.mybatis.mapper.CommentMapper;
import xb.dev.tools.mybatis.mapper.NewsMapper;
import xb.dev.tools.mybatis.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-11 11:23:48
 */
@SpringBootTest(classes = MybatisApplication.class)
@RunWith(SpringRunner.class)
public class NewsServiceTest {
    @Resource
    private CommentMapper commentMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private NewsMapper newsMapper;

    @Test
    public void testInsertComment(){
        Date now = new Date();
        int userMax = userMapper.queryMaxId();
        int userLimit = userMax + 100;
        int newsMax = newsMapper.queryMaxId();
        int commentMax = commentMapper.queryMaxId();
        for(int i=userMax+1;i<userLimit;i++){
            //添加用户
            UserEntity userEntity = new UserEntity();
            userEntity.setCreateTime(now);
            userEntity.setUserName("");
            userEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");
            String username = "Test Account "+i;
            userEntity.setUserName(username);
            userEntity.setUserId(Long.valueOf(i));
            userMapper.insert(userEntity);
            for(int j=1;j<10;j++){
                newsMax++;
                //添加新闻
                NewsEntity newsEntity = new NewsEntity();
                newsEntity.setUserId(i);
                newsEntity.setNewsId("XB2019021110000000");
                newsEntity.setAuthor(username);
                newsEntity.setBrowseCount((long) 0);
                newsEntity.setCreateTime(now);
                newsEntity.setBody("Test Body");
                newsEntity.setDeleteFlag((byte) 0);
                newsEntity.setOpposeCount((long) 0);
                newsEntity.setType("national");
                newsEntity.setSource("system");
                newsEntity.setTitle("Test Title");
                newsEntity.setStatus((byte) 1);
                newsEntity.setSupportCount((long) 0);
                newsEntity.setId(newsMax);
                newsMapper.insertNews(newsEntity);
                for(int k=0;k<10;k++){
                    commentMax++;
                    //添加评论
                    Comment comment = new Comment();
                    comment.setCreateTime(now);
                    comment.setCommentContent("Test Content");
                    comment.setIsDelete((byte) 0);
                    comment.setNewsId(newsMax);
                    comment.setUserId(i);
                    comment.setId(commentMax);
                    commentMapper.insert(comment);
                }
            }
        }
    }
}
