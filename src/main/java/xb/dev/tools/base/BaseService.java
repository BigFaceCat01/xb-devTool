package xb.dev.tools.base;

import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-03 15:58:57
 * @Description: 基础服务类
 */
public interface BaseService {
    /**
     * 查询新闻列表
     * @return
     */
    List<NewsEntity> queryAll() throws XbServiceException;

    /**
     * 根据id查询一条新闻
     * @return
     */
    NewsEntity queryOne(String id) throws XbServiceException;

    /**
     * 添加一条新闻
     * @param newsEntity
     * @throws XbServiceException
     */
    void insertNews(NewsEntity newsEntity) throws XbServiceException;

    /**
     * 删除新闻
     * @param id
     * @throws XbServiceException
     */
    void deleteNews(String id) throws XbServiceException;

    /**
     * 修改新闻
     * @param newsEntity
     * @throws XbServiceException
     */
    void updateNews(NewsEntity newsEntity) throws XbServiceException;
}
