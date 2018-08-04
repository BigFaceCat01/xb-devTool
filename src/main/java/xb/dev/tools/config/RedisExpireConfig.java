package xb.dev.tools.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-04 17:06:04
 * @Description:
 */

public class RedisExpireConfig implements CommandLineRunner {
    @Autowired
    private Jedis jedis;
    /**
     * 是否开启过期提醒
     */
    @Value("${redis.openExpireNotify}")
    private boolean openExpireNotify;
    @Override
    public void run(String... args) throws Exception {
        if(openExpireNotify){
            List<String> notify = jedis.configGet("notify-keyspace-events");
            if(notify.get(1).equals("")){
                jedis.configSet("notify-keyspace-events","Ex");

            }
            jedis.psubscribe(new JedisPubSub() {
                @Override
                public void onPMessage(String pattern, String channel, String message) {
                    System.out.println("################################");
                    System.out.println(jedis.get(message));
                }

                @Override
                public void onPSubscribe(String pattern, int subscribedChannels) {
                    System.out.println("发生一次订阅");
                }
            }, "__keyevent@0__:expired");
        }
    }
}
