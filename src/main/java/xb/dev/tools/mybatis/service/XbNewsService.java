package xb.dev.tools.mybatis.service;

import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.mybatis.dao.entity.XbNewsEntity;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-02 14:00:03
 * @Description:
 */
public interface XbNewsService {
    /**
     * 查询新闻列表
     * @return
     */
    List<XbNewsEntity> queryAll() throws XbServiceException;

    /**
     * 添加一条新闻
     * @param xbNewsEntity
     * @throws XbServiceException
     */
    void insertNews(XbNewsEntity xbNewsEntity) throws XbServiceException;

    /**
     * 删除新闻
     * @param id
     * @throws XbServiceException
     */
    void deleteNews(String id) throws XbServiceException;
}
