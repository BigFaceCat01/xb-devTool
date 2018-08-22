package xb.dev.document.temp;

import java.lang.annotation.*;

/**
 * 表格注解，该注解声明表格整体属性，用于实体类上，可以不使用
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Excel {

    /**
     * 表格中工作表解析范围，从1开始默认解析全部工作表,结合sheetTo，使用，也可以单独使用
     * @return
     */
    public int sheetFrom() default -1;

    /**
     * 表格中工作表解析范围，从1开始，与sheetFrom结合使用，单独声明该字段将没有作用，不包含to字段所代表的表，即1-5，不包含第五个表
     * @return
     */
    public int sheetTo() default -1;

}
