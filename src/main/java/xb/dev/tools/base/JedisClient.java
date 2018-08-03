package xb.dev.tools.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

/**
 * @Author: Created by huangxb on 2018-08-03 15:34:02
 * @Description:
 */
@Configuration
public class JedisClient {
    @Value("${spring.redis.host}")
    private String host;

    @Bean
    public Jedis getJedis(){
        return new Jedis(host);
    }
}
