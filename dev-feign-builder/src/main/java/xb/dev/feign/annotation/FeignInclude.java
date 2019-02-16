package xb.dev.feign.annotation;


import java.lang.annotation.*;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-01 11:32:39
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignInclude {
    /**
     * 方法名称，默认与被注解的方法名称一样
     * @return
     */
    String methodName() default "";

    /**
     * feign应用名称
     * @return
     */
    String applicationName();
}
