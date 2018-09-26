package xb.dev.tools.model.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * @author Created by huangxb
 * @date 2018-09-25 14:59:31
 */
@Data
public class MongoCommentModel {
    /**
     * 评论id
     */
    @Id
    private String commentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 评论用户id
     */
    private Long userId;
    /**
     * 评论用户名称
     */
    private String userName;
    /**
     * 父评论id
     */
    private String parentId;
}
