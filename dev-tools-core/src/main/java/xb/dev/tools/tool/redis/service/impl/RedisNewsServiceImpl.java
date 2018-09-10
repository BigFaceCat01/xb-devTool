package xb.dev.tools.tool.redis.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import xb.dev.tools.base.JedisClient;
import xb.dev.tools.common.Result;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.redis.service.RedisNewsService;

import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-02 14:00:21
 * @Description:
 */
@Service
public class RedisNewsServiceImpl implements RedisNewsService {

    private Jedis jedis = JedisClient.getJedis();

    @Override
    public void insertNewsWithTimeout(NewsEntity newsEntity, String key, int second) throws XbServiceException {

    }

    @Override
    public Result<List<NewsEntity>> queryAll() throws XbServiceException {
        return null;
    }

    @Override
    public Result<NewsEntity> queryOne(String s) throws XbServiceException {
        return null;
    }

    @Override
    public Result<Boolean> insert(NewsEntity newsEntity) throws XbServiceException {
        return null;
    }

    @Override
    public Result<Boolean> delete(String s) throws XbServiceException {
        return null;
    }

    @Override
    public Result<Boolean> update(NewsEntity newsEntity) throws XbServiceException {
        return null;
    }
}
