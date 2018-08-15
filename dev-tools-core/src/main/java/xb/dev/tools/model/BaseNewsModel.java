package xb.dev.tools.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import xb.dev.tools.dao.entity.Comment;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-15 11:10:24
 * @Description:
 */
@Getter
@Setter
public class BaseNewsModel implements Serializable {
    /**
     * 记录id
     */
    @ApiModelProperty("新闻记录id")
    private Integer id;
    /**
     * 新闻编号
     */
    @ApiModelProperty("新闻编号")
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
     * 新闻状态
     */
    @ApiModelProperty("新闻状态")
    private Byte status;
    /**
     * 评论列表
     */
    @ApiModelProperty("评论列表")
    private List<Comment> comments;
    /**
     * 是否已删除
     */
    @ApiModelProperty("是否已删除")
    private Boolean deleteFlag;
}
