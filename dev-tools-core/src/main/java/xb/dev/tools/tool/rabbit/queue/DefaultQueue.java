package xb.dev.tools.tool.rabbit.queue;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xb.dev.tools.constant.RabbitConstant;

/**
 * @author Created by huangxb on 2018-08-03 17:14:54
 *
 */
@Configuration
public class DefaultQueue {

    /**
     * hello队列
     * @return hello队列
     */
    @Bean
    public Queue  helloQueue(){//返回一个队列，名称为hello
        return new Queue(RabbitConstant.HELLO_QUEUE);
    }

    /**
     *完全匹配路由的交换机
     * @return 完全匹配路由的交换机
     */
    @Bean
    public DirectExchange helloQueueDirectExchange(){
        return new DirectExchange(RabbitConstant.HELLO_DIRECT_EXCHANGE);
    }

    /**
     * 将hello队列绑定到完全匹配路由的交换机上
     * @param helloQueue hello队列
     * @param helloQueueDirectExchange 完全匹配路由的交换机
     * @return 绑定
     */
    @Bean
    public Binding helloQueueBindingExchange(Queue helloQueue,DirectExchange helloQueueDirectExchange){
        return BindingBuilder.bind(helloQueue).to(helloQueueDirectExchange).with(RabbitConstant.HELLO_DIRECT_KEY);
    }
}
