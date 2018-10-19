package xb.dev.tools.mongodb.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Created by huangxb
 * @date 2018-09-25 18:01:52
 */
@Data
public class MongoNewsInsertModel implements Serializable {
    /**
     * 新闻标题
     */
    @ApiModelProperty("新闻标题")
    @NotNull
    @Size(max=500)
    private String title;
    /**
     * 新闻正文
     */
    @ApiModelProperty("新闻正文")
    @NotNull
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
