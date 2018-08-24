package xb.dev.document.temp;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Created by huangxb on 2018-08-23 16:09:06
 * @Description:
 */
@Getter
@Setter
@Excel(sheetFrom = 0,sheetTo = 1)
public class GoodsPropData {
    @TableHead(name = "所属一级类目")
    private String one;
    @TableHead(name = "所属二级类目")
    private String two;
    @TableHead(name = "所属三级类目")
    private String three;
    @TableHead(name = "参数名称（中文）")
    private String propCn;
    @TableHead(name = "参数名称（英文）")
    private String propEn;
    @TableHead(name = "必填可选")
    private String mustFill;
    @TableHead(name = "参数类型")
    private String propType;
    @TableHead(name = "参数值（中文）")
    private String propValueCn;
    @TableHead(name = "参数值（英文）")
    private String propValueEn;
    @TableHead(name = "规格类型（中文）")
    private String specsCn;
    @TableHead(name = "规格类型（英文）")
    private String specsEn;
    @TableHead(name = "规格值（中文）")
    private String specsValueCn;
    @TableHead(name = "规格值（英文）")
    private String specsValueEn;
}
