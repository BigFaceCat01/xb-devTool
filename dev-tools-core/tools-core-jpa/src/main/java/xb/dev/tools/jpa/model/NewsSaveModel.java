package xb.dev.tools.jpa.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-21 16:23:57
 */
@Data
public class NewsSaveModel implements Serializable {
    /**
     * 新闻标题
     */
    private String title;
    /**
     * 新闻正文
     */
    private String body;
    /**
     * 新闻来源
     */
    private String source;
    /**
     * 新闻分类标签
     */
    private String type;
}
