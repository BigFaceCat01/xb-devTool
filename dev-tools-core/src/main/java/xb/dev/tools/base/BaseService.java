package xb.dev.tools.base;

import xb.dev.tools.common.Result;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-03 15:58:57
 * @Description: 基础服务类
 */
public interface BaseService<T,ID> {
    /**
     * 查询记录列表
     * @return
     */
    Result<List<T>> queryAll() throws XbServiceException;

    /**
     * 根据id查询一条记录
     * @return
     */
    Result<T> queryOne(ID id) throws XbServiceException;

    /**
     * 向数据库添加一条记录
     * @param t
     * @throws XbServiceException
     */
    Result<Boolean> insert(T t) throws XbServiceException;

    /**
     * 删除记录
     * @param id
     * @throws XbServiceException
     */
    Result<Boolean> delete(ID id) throws XbServiceException;

    /**
     * 修改记录
     * @param t
     * @throws XbServiceException
     */
    Result<Boolean> update(T t) throws XbServiceException;
}
