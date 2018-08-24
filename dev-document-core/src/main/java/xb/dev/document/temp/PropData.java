package xb.dev.document.temp;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: Created by huangxb on 2018-08-23 09:27:36
 * @Description:
 */
@Getter
@Setter
@Excel(sheetFrom = 0,sheetTo = 1)
public class PropData {
    @TableHead(name = "参数名称（英文）")
    private String prop;
    @TableHead(name = "所属一级类目（英文）")
    private String one;
    @TableHead(name = "所属二级类目（英文）")
    private String two;
    @TableHead(name = "所属三级类目（英文）")
    private String three;

    @Override
    public String toString() {
        return "PropData{" +
                "prop='" + prop + '\'' +
                ", one='" + one + '\'' +
                ", two='" + two + '\'' +
                ", three='" + three + '\'' +
                '}';
    }
}
