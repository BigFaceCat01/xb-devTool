package xb.dev.tools.tool.redis.listener;

import redis.clients.jedis.JedisPubSub;

/**
 * @Author: Created by huangxb on 2018-08-04 17:40:15
 * @Description:
 */
public class RedisExpireNotify extends JedisPubSub {
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(message+"过期");
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("订阅");
    }
}
