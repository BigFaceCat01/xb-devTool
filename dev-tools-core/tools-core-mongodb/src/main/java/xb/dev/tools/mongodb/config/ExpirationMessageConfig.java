package xb.dev.tools.mongodb.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-06 14:03:13
 */
public class ExpirationMessageConfig implements MessagePostProcessor {
    /**
     * 消息延迟时间
     */
    private long ttl;

    public ExpirationMessageConfig(long ttl){
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setExpiration(String.valueOf(ttl));
        return message;
    }
}
