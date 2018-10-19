package xb.dev.tools.mongodb.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;

/**
 * @author Created by huangxb
 * @date 2018-09-25 14:58:05
 */
@Data
public class MongoNewsModel {
    /**
     * 新闻id
     */
    @Id
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
     * 用户id
     */
    private Long userId;
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
     * 评论id列表
     */
    private List<String> commentIds;
    /**
     * 是否已删除
     */
    private Boolean deleteFlag;
    /**
     * 修改时间
     */
    private Date updateTime;
}
