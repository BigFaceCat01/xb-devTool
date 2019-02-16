package xb.dev.feign.annotation;


import java.lang.annotation.*;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-01 11:32:39
 */
@Target({ElementType.TYPE})
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignComponent {
}
