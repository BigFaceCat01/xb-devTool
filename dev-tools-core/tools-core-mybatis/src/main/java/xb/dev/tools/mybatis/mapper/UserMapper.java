package xb.dev.tools.mybatis.mapper;

import xb.dev.tools.mybatis.dao.entity.UserEntity;
import xb.dev.tools.mybatis.model.UserLoginInfoModel;

import java.util.Map;

/**
 * @author Created by huang xiao bao
 * @date 2019-02-11 14:02:10
 */
public interface UserMapper {
    /**
     * 返回数据表当前最大id
     * @return
     */
    int queryMaxId();
    /**
     * 新增
     * @param userEntity 用户信息
     * @return
     */
    int insert(UserEntity userEntity);
    /**
     * 根据用户名和密码查询用户
     * @param params 用户名和密码
     * @return
     */
    UserLoginInfoModel login(Map<String,String> params);
}
