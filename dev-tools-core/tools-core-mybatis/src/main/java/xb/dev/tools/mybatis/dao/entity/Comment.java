package xb.dev.tools.mybatis.dao.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by huangxb on 2018-08-01 18:02:33
 * 新闻评论实体类
 */
@Data
public class Comment implements Serializable {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 评论人id
     */
    private Integer userId;
    /**
     * 评论新闻id
     */
    private Integer newsId;
    /**
     * 评论内容
     */
    private String commentContent;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 是否删除
     */
    private Byte isDelete;
    /**
     * 修改时间
     */
    private Date updateTime;
}
