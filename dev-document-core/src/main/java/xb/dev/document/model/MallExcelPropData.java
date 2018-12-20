package xb.dev.document.model;

import lombok.Data;
import xb.dev.document.temp.TableHead;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-19 10:15:47
 */
@Data
public class MallExcelPropData {
    @TableHead(name = "所属类目")
    private String category;
    /**
     * 属性中文
     */
    @TableHead(name="属性中文")
    private String propZh;
    /**
     * 属性英文
     */
    @TableHead(name="属性英文")
    private String propEn;
    /**
     * 属性类型：1普通属性，2销售文字属性，3销售图片属性
     */
    @TableHead(name = "属性类别")
    private String propType;
    /**
     *是否必选：0不必选，1必选
     */
    @TableHead(name = "必选")
    private String required;
    /**
     * 输入类型：0单选，1多选，2输入
     */
    @TableHead(name="输入类型")
    private String inputType;
    /**
     * 是否导航属性（必须是单选或多选）：0不是，1是
     */
    @TableHead(name="导航")
    private String nav;
    /**
     * 属性值中文
     */
    @TableHead(name = "属性值")
    private String propValueZh;
    /**
     * 属性值英文
     */
    @TableHead(name = "属性值英文")
    private String propValueEn;

}
