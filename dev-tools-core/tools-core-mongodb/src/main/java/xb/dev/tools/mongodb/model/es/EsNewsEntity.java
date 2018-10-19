package xb.dev.tools.mongodb.model.es;

import lombok.Data;

import java.util.Date;

/**
 * @author Created by huangxb
 * @date 2018-10-12 11:01:58
 */
@Data
public class EsNewsEntity {
    /**
     * 新闻标识
     */
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
}
