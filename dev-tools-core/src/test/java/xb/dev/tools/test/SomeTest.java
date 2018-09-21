package xb.dev.tools.test;

import xb.dev.tools.dao.entity.Comment;
import xb.dev.tools.dao.entity.NewsEntity;

import javax.swing.text.html.parser.Entity;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: Created by huangxb on 2018-08-03 14:51:28
 * @Description:
 */
public class SomeTest {

    private static final Random r = new Random();

    public static void main(String[] args){
//        System.out.println("1^2^3".split("\\^").length);
        System.out.println();
        Map<String,Object> params = new HashMap<>(156);
        params.put("one",1);
        params.put("one",2);
        params.put("two",2);
        params.put("three",3);
        params.put("four",4);
        Set<Map.Entry<String,Object>> r = params.entrySet();
        params.forEach((key,value)->
            System.out.println(key+":"+value)
        );
        System.out.println();

    }


    public static NewsEntity buildNewsEntity(){
        NewsEntity newsModel = new NewsEntity();
        String s =new SimpleDateFormat("yyyyMMdd").format(new Date());
        newsModel.setNewsId("XB"+s+"00005");
        newsModel.setAuthor("zhao si");
        newsModel.setBody("body create by zhao si");
        newsModel.setBrowseCount(r.nextLong()*10000+1000);
        newsModel.setCreateTime(new Date());
        newsModel.setOpposeCount(r.nextLong()*300);
        newsModel.setSource("BILI BILI");
        newsModel.setTitle("title create by zhao si");
        newsModel.setType("math,computer");
        newsModel.setStatus((byte)1);
        newsModel.setSupportCount(r.nextLong()*10000+1000);
        newsModel.setDeleteFlag(false);
        return newsModel;
    }

    public static NewsEntity buildWithComment(){
        NewsEntity newsEntity = buildNewsEntity();
        Comment comment = new Comment();
        comment.setStatus((byte)1);
        comment.setCommentTime(new Date());
        comment.setContent("this is a comment");
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment);
        newsEntity.setComments(commentList);
        return newsEntity;
    }
}
