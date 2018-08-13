package xb.dev.tools.tool.rabbit.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xb.dev.tools.constant.RabbitConstant;

/**
 * @Author: Created by huangxb on 2018-08-03 17:14:54
 * @Description:
 */
@Configuration
public class DefaultQueue {

    @Bean
    public Queue  helloQueue(){//返回一个队列，名称为hello
        return new Queue(RabbitConstant.HELLO_QUEUE);
    }
}
