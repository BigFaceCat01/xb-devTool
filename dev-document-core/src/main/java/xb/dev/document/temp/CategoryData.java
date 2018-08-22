package xb.dev.document.temp;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: Created by huangxb on 2018-08-20 18:07:17
 * @Description:
 */
@Getter
@Setter
@Excel(sheetFrom = 0,sheetTo = 1)
public class CategoryData implements Serializable {
    @TableHead(name = "零级英文")
    private String zero;
    @TableHead(name = "一级英文")
    private String one;
    @TableHead(name = "二级英文")
    private String two;
    @TableHead(name = "三级英文")
    private String three;
}
