package xb.dev.document.model;

import lombok.Data;
import xb.dev.document.temp.Excel;
import xb.dev.document.temp.TableHead;

import java.io.Serializable;

/**
 * @author Created by huangxb on 2018-08-20 18:07:17
 * @Description:
 */
@Data
@Excel(sheetFrom = 1,sheetTo = 2)
public class BackCategoryData implements Serializable {
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
    @TableHead(name = "四级类目的中文名称")
    private String three;
    @TableHead(name = "四级类目英文名称")
    private String threeEn;
}
