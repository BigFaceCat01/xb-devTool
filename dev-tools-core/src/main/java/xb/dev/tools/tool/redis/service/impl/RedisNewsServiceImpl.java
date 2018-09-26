package xb.dev.tools.tool.redis.service.impl;

import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import xb.dev.tools.base.JedisClient;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.redis.service.RedisNewsService;

/**
 * @author Created by huangxb on 2018-08-02 14:00:21
 *
 */
@Service
public class RedisNewsServiceImpl implements RedisNewsService {

    private Jedis jedis = JedisClient.getJedis();

    @Override
    public void insertNewsWithTimeout(NewsEntity newsEntity, String key, int second) throws XbServiceException {

    }

    @Override
    public NewsEntity queryOne(String s) {
        return null;
    }

    @Override
    public void insert(NewsEntity newsEntity) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public void deleteWithLogic(String s) {

    }

    @Override
    public void update(NewsEntity newsEntity) {

    }
}
