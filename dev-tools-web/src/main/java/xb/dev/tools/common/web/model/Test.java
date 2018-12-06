package xb.dev.tools.common.web.model;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Created by huang xiao bao
 * @date 2018-11-28 11:42:46
 */
@Data
public class Test implements Serializable {
    @NotNull(message = "空")
    private String name;
    @Size(max = 12)
    private String id;
    @Digits(integer = Integer.MAX_VALUE,fraction = 0,message = "只能是数字")
    private Integer age =1;
}
