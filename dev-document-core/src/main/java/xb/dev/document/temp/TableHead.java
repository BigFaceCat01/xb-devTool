package xb.dev.document.temp;

import java.lang.annotation.*;

/**
 * 表头注解，该注解声明的属性在excel表格中的表头名称，即对应哪一列的数据，用在属性或方法上<br/>
 * 可以不使用，不使用该注解将默认使用属性名当作表头
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
@Documented
public @interface TableHead {
    /**
     * 表头名称,不声明将使用字段属性名代替
     * @return
     */
    public String name() default "";

    /**
     * 若该属性为日期，可指定日期转换格式，默认为yyyy/MM/dd
     * @return
     */
    public String dateFormat() default "yyyy/MM/dd";




}
