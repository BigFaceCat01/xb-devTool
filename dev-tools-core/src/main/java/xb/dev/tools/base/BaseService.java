package xb.dev.tools.base;

/**
 * @author: Created by huangxb on 2018-08-03 15:58:57
 * 基础服务类
 */
public interface BaseService<T,ID> {
    /**
     * 根据id查询一条记录
     * @param id 数据id
     * @return id对应的对象
     */
    T queryOne(ID id);

    /**
     * 向数据库添加一条记录
     * @param t 插入的实体对象
     */
    void insert(T t);

    /**
     * 删除记录
     * @param id id
     */
    void delete(ID id);

    /**
     * 逻辑删除记录
     * @param id id
     */
    void deleteWithLogic(ID id);

    /**
     * 修改记录
     * @param t 修改实体
     */
    void update(T t);
}
