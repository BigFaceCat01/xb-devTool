package xb.dev.tools.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by huangxb
 * @date 2018-09-26 11:55:10
 */
@Data
public class MongoNewsListModel implements Serializable {
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
     * 新闻来源
     */
    @ApiModelProperty("新闻来源")
    private String source;
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

    @ApiModelProperty("新闻状态")
    private Byte status;
}
