package xb.dev.document.temp;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
/**
 * @Author: Created by huangxb on 2018-08-23 18:02:41
 * @Description:
 */
@Getter
@Setter
public class GoodsPropInsert {
    //一级类目名称
    private String one;
    //二级类目名称
    private String two;
    //三级类目名称
    private String three;
    //参数list
    private List<GoodsProp> propList;

}
