package xb.dev.tools.tool.redis.service;

import xb.dev.tools.base.BaseService;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Created by huangxb on 2018-08-02 14:00:03
 * @Description:
 */
public interface RedisNewsService extends BaseService<NewsEntity,String> {
    /**
     * 新增新闻，该新闻会在定义时间后过时
     * @param newsEntity
     * @param key
     * @param second 秒数
     * @throws XbServiceException
     */
    public void insertNewsWithTimeout(NewsEntity newsEntity, String key, int second) throws XbServiceException;
}
