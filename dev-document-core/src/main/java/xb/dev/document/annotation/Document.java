package xb.dev.document.annotation;

import com.zhongfei.data.document.common.DocumentType;

import java.lang.annotation.*;

/**
 * @Author: Created by huangxb on 2018-07-27 09:49:31
 * @Description:
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Document {
    /**
     * 文档类型，当文档类型为表格时，只处理第一个工作表的数据，所以请将数据放在第一个工作表中
     */
    public DocumentType documentType();

    /**
     * 日期格式转换，默认"yyyy-MM-dd"
     * @return
     */
    public String dateFormat() default "yyyy-MM-dd";

    /**
     * 日期时间格式转换，默认"yyyy-MM-dd hh:mm:ss"
     * @return
     */
    public String datetimeFormat() default "yyyy-MM-dd hh:mm:ss";
}
