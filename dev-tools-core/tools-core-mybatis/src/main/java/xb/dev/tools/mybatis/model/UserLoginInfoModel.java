package xb.dev.tools.mybatis.model;

import lombok.Data;

/**
 * 用户成功登录返回的用户信息
 * @author Created by huangxb
 * @date 2018-10-19 16:41:38
 */
@Data
public class UserLoginInfoModel {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
}
