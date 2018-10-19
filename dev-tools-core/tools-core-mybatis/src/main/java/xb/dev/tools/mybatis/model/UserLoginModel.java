package xb.dev.tools.mybatis.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户登录时填写的字段信息
 * @author Created by huangxb
 * @date 2018-10-19 16:45:54
 */
@Data
public class UserLoginModel {
    /**
     * 密码
     */
    @NotNull
    @ApiModelProperty("密码")
    private String password;
    /**
     * 用户名
     */
    @NotNull
    @ApiModelProperty("用户名")
    private String username;
}
