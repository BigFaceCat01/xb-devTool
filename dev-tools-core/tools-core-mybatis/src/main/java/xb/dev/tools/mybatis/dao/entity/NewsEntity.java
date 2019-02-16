package xb.dev.tools.mybatis.dao.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Created by huangxb on 2018-08-01 17:58:24
 * 新闻实体类
 */
@Data
public class NewsEntity implements Serializable {
    @Id
    private Integer id;
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
     * 是否已删除
     */
    private Byte deleteFlag;
    /**
     * 用户id
     *
     */
    private Integer userId;
}
