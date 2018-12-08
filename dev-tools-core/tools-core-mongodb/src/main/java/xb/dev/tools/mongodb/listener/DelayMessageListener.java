package xb.dev.tools.mongodb.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import xb.dev.tools.mongodb.config.RabbitConfig;

/**
 * @author Created by huang xiao bao
 * @date 2018-12-06 13:57:46
 */
@Service
@Slf4j
@RabbitListener(queues = RabbitConfig.DELAY_CONSUME_QUEUE)
public class DelayMessageListener {

    @RabbitHandler
    public void process(String msg){
        log.info("收到消息:{}",msg);
    }
}
