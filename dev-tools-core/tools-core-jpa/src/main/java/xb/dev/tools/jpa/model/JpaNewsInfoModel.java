package xb.dev.tools.jpa.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by huangxb
 * @date 2018-10-11 14:50:20
 */
@Data
public class JpaNewsInfoModel implements Serializable {
    /**
     * 新闻id
     */
    @ApiModelProperty("新闻id")
    private Integer id;

    /**
     * 新闻标识
     */
    @ApiModelProperty("新闻标识")
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
     * 是否已删除
     */
    @ApiModelProperty("是否已删除")
    private Boolean deleteFlag;
    /**
     * 用户id
     */
    @ApiModelProperty("用户id")
    private Integer userId;
}
