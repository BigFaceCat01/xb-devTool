package xb.dev.document.annotation;

import java.lang.annotation.*;

/**
 * @Author: Created by huangxb on 2018-07-27 09:59:18
 * @Description:
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface Head {
    /**
     * 数据所属的头名称
     * @return
     */
    public String name();
}
