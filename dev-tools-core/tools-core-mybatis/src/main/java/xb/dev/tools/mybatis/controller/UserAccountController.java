package xb.dev.tools.mybatis.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xb.dev.tools.common.Result;
import xb.dev.tools.mybatis.code.CodeEnum;
import xb.dev.tools.mybatis.model.UserLoginInfoModel;
import xb.dev.tools.mybatis.model.UserLoginModel;
import xb.dev.tools.mybatis.service.UserAccountService;

import javax.validation.Valid;

/**
 * @author Created by huangxb
 * @date 2018-10-19 16:40:08
 */
@RestController
@RequestMapping("/user")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;


    @ApiOperation(value = "用户登录接口",httpMethod = "POST")
    @ApiImplicitParam(paramType = "body",dataType = "UserLoginModel",name = "loginModel",value = "登录信息")
    @PostMapping("login")
    public Result<UserLoginInfoModel> login(@RequestBody @Valid UserLoginModel loginModel){
        return Result.build(CodeEnum.SUCCESS.getCode(),userAccountService.login(loginModel));
    }
}
