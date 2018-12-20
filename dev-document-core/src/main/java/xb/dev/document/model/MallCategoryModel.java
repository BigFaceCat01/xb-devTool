package xb.dev.document.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-14 09:13:30
 */
@Data
public class MallCategoryModel implements Serializable {
    /**
     * 类目中文
     */
    private String categoryNameCn;
    /**
     * 类目英文
     */
    private String categoryNameEn;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 关联
     */
    private List<String> related;
    /**
     * 子类目
     */
    private Map<String,MallCategoryModel> childs;
}
