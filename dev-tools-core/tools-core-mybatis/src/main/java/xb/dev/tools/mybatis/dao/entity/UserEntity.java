package xb.dev.tools.mybatis.dao.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Created by huangxb
 * @date 2018-10-19 17:47:25
 */
@Data
public class UserEntity {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 创建时间
     */
    private Date createTime;
}
