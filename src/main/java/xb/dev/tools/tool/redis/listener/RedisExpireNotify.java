package xb.dev.tools.tool.redis.listener;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import xb.dev.tools.base.JedisClient;

import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-04 17:40:15
 * @Description:
 */
public class RedisExpireNotify extends JedisPubSub {
    private Jedis jedis = JedisClient.getJedis();
    @Override
    public void onPMessage(String pattern, String channel, String message) {

        System.out.println(message+"过期");
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        System.out.println("订阅");
    }

    public static void main(String[] args){
        Jedis jedis = JedisClient.getJedis();
        List<String> notify = jedis.configGet("notify-keyspace-events");
        if (notify.get(1).equals("")) {
            jedis.configSet("notify-keyspace-events", "Ex");

        }
        jedis.psubscribe(new RedisExpireNotify(),"__keyevent@0__:expired");
    }
}
