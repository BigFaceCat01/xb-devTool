package xb.dev.tools.mybatis.service;

import xb.dev.tools.mybatis.model.UserLoginInfoModel;
import xb.dev.tools.mybatis.model.UserLoginModel;

/**
 * @author Created by huangxb
 * @date 2018-10-19 16:18:06
 */
public interface UserAccountService {
    /**
     * 用户登录
     * @param loginModel 登录信息
     * @return 用户信息
     */
    UserLoginInfoModel login(UserLoginModel loginModel);
}
