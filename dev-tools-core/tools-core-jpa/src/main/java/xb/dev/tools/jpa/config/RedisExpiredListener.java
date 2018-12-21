package xb.dev.tools.jpa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author wy
 */
@Component
@Slf4j
public class RedisExpiredListener implements MessageListener {

    /**
     * redis 的 key 过期通知事件
     */
    @Override
    public void onMessage(Message message, byte[] bytes) {
        // 建议使用: valueSerializer
        byte[] body = message.getBody();
        String mes = new String(body);
        log.info("redis key[" + mes + "] 过期");
    }
}
