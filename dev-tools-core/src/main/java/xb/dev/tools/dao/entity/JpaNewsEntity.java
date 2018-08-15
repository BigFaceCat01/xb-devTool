package xb.dev.tools.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-15 11:09:34
 * @Description:
 */
@Entity
@Table(name = "xb_news", schema = "test", catalog = "")
public class JpaNewsEntity implements Serializable {
    /**
     * 新闻id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    @Column(name = "news_id")
    private String newsId;
    /**
     * 新闻标题
     */
    @Basic
    @Column(name = "title")
    private String title;
    /**
     * 新闻正文
     */
    @Basic
    @Column(name = "body")
    private String body;
    /**
     * 新闻来源
     */
    @Basic
    @Column(name = "source")
    private String source;
    /**
     * 新闻作者
     */
    @Basic
    @Column(name = "author")
    private String author;
    /**
     * 新闻创建时间
     */
    @Basic
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 新闻分类标签
     */
    @Basic
    @Column(name = "type")
    private String type;
    /**
     * 浏览数量
     */
    @Basic
    @Column(name = "browse_count")
    private Long browseCount;
    /**
     * 点赞数量
     */
    @Basic
    @Column(name = "support_count")
    private Long supportCount;
    /**
     * 差评数量
     */
    @Basic
    @Column(name = "oppose_count")
    private Long opposeCount;
    /**
     * 新闻状态
     */
    @Basic
    @Column(name = "status")
    private Byte status;
    /**
     * 是否已删除
     */
    @Basic
    @Column(name = "deleteFlag")
    private Boolean deleteFlag;
}
