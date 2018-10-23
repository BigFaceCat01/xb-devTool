package xb.dev.tools.es.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author Created by huangxb
 * @date 2018-10-22 13:48:46
 */
@RabbitListener(queues = "enterprise.queue-goods")
@Slf4j
@Service
public class TempListener {
    @RabbitHandler
    public void process(String msg){
        log.info("收到消息:{}",msg);
    }}
