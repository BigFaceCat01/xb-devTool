package xb.dev.tools.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import xb.dev.tools.dao.entity.Comment;
import java.util.List;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Created by huangxb on 2018-08-01 17:58:24
 * @Description: 新闻实体类
 */
@Getter
@Setter
public class NewsInsertModel implements Serializable {
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
     * 新闻分类标签
     */
    @ApiModelProperty("新闻分类标签")
    private String type;
}
