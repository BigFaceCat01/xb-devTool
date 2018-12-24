package xb.dev.tools.es.dao.entity;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Created by huangxb
 * @date 2018-10-12 11:01:58
 */
@Data
public class EsNewsEntity implements Serializable {
    /**
     * 新闻标识
     */
    @Id
    private String newsId;
    /**
     * 标题
     */
    private String title;
    /**
     * 作者名称
     */
    private String author;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 浏览量
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
     * 来源
     */
    private String source;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 分类标签
     */
    private String type;

    /**
     * 新闻主图
     */
    private String img;

    /**
     * 新闻关键词
     */
    private List<String> keywords;
}
