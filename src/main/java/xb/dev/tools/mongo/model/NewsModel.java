package xb.dev.tools.mongo.model;

import lombok.Getter;
import lombok.Setter;
import xb.dev.tools.mongo.dao.entity.Comment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-01 17:58:24
 * @Description: 新闻实体类
 */
@Getter
@Setter
public class NewsModel implements Serializable {
    /**
     * 新闻id
     */
    private String newsId;
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻正文
     */
    private String body;
    /**
     * 新闻来源
     */
    private String source;
    /**
     * 新闻作者
     */
    private String author;
    /**
     * 新闻创建时间
     */
    private Date createTime;
    /**
     * 新闻分类标签
     */
    private String type;
    /**
     * 浏览数量
     */
    private Long browseCount;
    /**
     * 点赞数量
     */
    private Long supportCount;
    /**
     * 差评数量
     */
    private Long opposeCount;
    /**
     * 新闻状态
     */
    private Byte status;
    /**
     * 评论列表
     */
    private List<Comment> comments;
}
