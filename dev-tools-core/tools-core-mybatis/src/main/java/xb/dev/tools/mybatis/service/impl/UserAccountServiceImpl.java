package xb.dev.tools.mybatis.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import xb.dev.tools.mybatis.base.BaseMapper;
import xb.dev.tools.mybatis.code.CodeEnum;
import xb.dev.tools.mybatis.exception.XbServiceException;
import xb.dev.tools.mybatis.model.UserLoginInfoModel;
import xb.dev.tools.mybatis.model.UserLoginModel;
import xb.dev.tools.mybatis.service.UserAccountService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Created by huangxb
 * @date 2018-10-19 16:19:04
 */
@Service
public class UserAccountServiceImpl implements UserAccountService {
    @Autowired
    private BaseMapper baseMapper;
    @Override
    public UserLoginInfoModel login(UserLoginModel loginModel) {
        Map<String,String> param = new HashMap<>(16);
        param.put("username",loginModel.getUsername());
        String md5Password = DigestUtils.md5DigestAsHex(loginModel.getPassword().getBytes()).toUpperCase();
        UserLoginInfoModel loginInfoModel = baseMapper.findForObject("UserMapper.login",param);

        if(loginInfoModel == null){
            throw new XbServiceException(CodeEnum.USER_ACCOUNT_USERNAME_OR_PASSWORD_WRONG.getCode());
        }

        return loginInfoModel;
    }
}
