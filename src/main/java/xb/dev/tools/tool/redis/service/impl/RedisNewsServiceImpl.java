package xb.dev.tools.tool.redis.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import xb.dev.tools.dao.entity.NewsEntity;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.redis.listener.RedisExpireNotify;
import xb.dev.tools.tool.redis.service.RedisNewsService;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Created by huangxb on 2018-08-02 14:00:21
 * @Description:
 */
@Service
public class RedisNewsServiceImpl implements RedisNewsService {
    @Autowired
    private Jedis jedis;

    @Override
    public List<NewsEntity> queryAll() throws XbServiceException {
        return null;
    }

    @Override
    public NewsEntity queryOne(String id) throws XbServiceException {
        String obj = jedis.get(id);
        return JSON.parseObject(obj,NewsEntity.class);
    }

    @Override
    public void insertNews(NewsEntity newsEntity) throws XbServiceException {
        String value = JSON.toJSONString(newsEntity);
        jedis.set(newsEntity.getNewsId(),value);
    }

    @Override
    public void deleteNews(String id) throws XbServiceException {

    }

    @Override
    public void updateNews(NewsEntity newsEntity) throws XbServiceException {

    }
    @Override
    public void insertNewsWithTimeout(NewsEntity newsEntity, String key, int second) throws XbServiceException{
        new Thread(new Runnable() {
            @Override
            public void run() {
                RedisExpireNotify redisExpireNotify = new RedisExpireNotify();
                jedis.psubscribe(redisExpireNotify,"__key*__:*");
            }
        }).start();

        String value = JSON.toJSONString(newsEntity);
        System.out.println(value);
        jedis.setex(key,second,value);
    }
}
