package xb.dev.tools.tool.rabbit.listener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import xb.dev.tools.constant.QueueConstant;

/**
 * @Author: Created by huangxb on 2018-08-03 17:26:55
 * @Description:
 */
@Component
public class DefaultQueueListener {
    @RabbitHandler
    @RabbitListener(queues = {QueueConstant.HELLO_QUEUE})
    public void receive(String msg){
        System.out.println("receive message from "+QueueConstant.HELLO_QUEUE+":"+msg);
    }
}
