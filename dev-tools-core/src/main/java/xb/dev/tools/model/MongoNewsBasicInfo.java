package xb.dev.tools.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author Created by huangxb
 * @date 2018-09-25 17:53:35
 */
@Data
public class MongoNewsBasicInfo {
    /**
     * 新闻id
     */
    @ApiModelProperty("新闻id")
    private String newsId;
    /**
     * 新闻标题
     */
    @ApiModelProperty("新闻标题")
    private String title;
    /**
     * 新闻正文
     */
    @ApiModelProperty("新闻正文")
    private String body;
    /**
     * 新闻来源
     */
    @ApiModelProperty("新闻来源")
    private String source;
    /**
     * 新闻作者
     */
    @ApiModelProperty("新闻作者")
    private String author;
    /**
     * 新闻创建时间
     */
    @ApiModelProperty("新闻创建时间")
    private Date createTime;
    /**
     * 新闻分类标签
     */
    @ApiModelProperty("新闻分类标签")
    private String type;
    /**
     * 浏览数量
     */
    @ApiModelProperty("浏览数量")
    private Long browseCount;
    /**
     * 点赞数量
     */
    @ApiModelProperty("点赞数量")
    private Long supportCount;
    /**
     * 差评数量
     */
    @ApiModelProperty("差评数量")
    private Long opposeCount;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Long userId;
    /**
     * 评论列表
     */
    @ApiModelProperty("评论列表")
    private List<MongoCommentBasicModel> comments;
}
