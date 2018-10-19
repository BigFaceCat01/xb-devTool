package xb.dev.tools.mongodb.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by huangxb
 * @date 2018-09-25 17:55:49
 */
@Data
public class MongoCommentBasicModel implements Serializable {
    /**
     * 评论id
     */
    @ApiModelProperty("评论id")
    private String commentId;
    /**
     * 评论内容
     */
    @ApiModelProperty("评论内容")
    private String content;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
     * 评论用户id
     */
    @ApiModelProperty("评论用户id")
    private Long userId;
    /**
     * 评论用户名称
     */
    @ApiModelProperty("评论用户名称")
    private String userName;
}
