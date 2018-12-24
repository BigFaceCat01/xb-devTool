package xb.dev.document.temp;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Created by huangxb on 2018-08-20 18:07:17
 * @Description:
 */
@Data
@Excel(sheetFrom = 0,sheetTo = 1)
public class CategoryData implements Serializable {
    @TableHead(name = "一级类目的中文名称")
    private String zero;
    @TableHead(name = "一级类目英文名称")
    private String zeroEn;
    @TableHead(name = "二级类目的中文名称")
    private String one;
    @TableHead(name = "二级类目英文名称")
    private String oneEn;
    @TableHead(name = "三级类目的中文名称")
    private String two;
    @TableHead(name = "三级类目英文名称")
    private String twoEn;
    @TableHead(name = "四级关联类目中文")
    private String related;
}
