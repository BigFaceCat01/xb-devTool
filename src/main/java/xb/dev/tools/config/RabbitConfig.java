package xb.dev.tools.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Created by huangxb on 2018-08-03 17:13:48
 * @Description: 消息配置
 */
@Configuration
public class RabbitConfig {
    public static final String PRODUCT_QUEUE = "productSearchQueue";
    public static final String ORDER_QUEUE = "orderQueue";
    public static final String PRODUCT_INSERT_QUEUE = "productInsertQueue";
    public static final String PRODUCT_UPDATE_QUEUE = "productUpdateQueue";
    public static final String PRODUCT_DELETE_QUEUE = "productDeleteQueue";
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        return factory;
    }
    @Bean
    public Queue productQueue(){
        return new Queue(PRODUCT_QUEUE);
    }
    @Bean
    public Queue orderQueue(){
        return new Queue(ORDER_QUEUE);
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("orderFanoutExchange");
    }
    @Bean
    public Binding bindingQueue(Queue orderQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(orderQueue).to(fanoutExchange);
    }

    @Bean
    public Queue productInsertQueue() {
        return new Queue(PRODUCT_INSERT_QUEUE);
    }

    @Bean
    public Queue productUpdateQueue() {
        return new Queue(PRODUCT_UPDATE_QUEUE);
    }

    @Bean
    public Queue productDeleteQueue() {
        return new Queue(PRODUCT_DELETE_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("productDirectExchange");
    }

    @Bean
    public Binding bindingDirectExchangeProductInsertQueue(Queue productInsertQueue,DirectExchange directExchange){
        return BindingBuilder.bind(productInsertQueue).to(directExchange).withQueueName();
    }
    @Bean
    public Binding bindingDirectExchangeProductUpdateQueue(Queue productUpdateQueue,DirectExchange directExchange){
        return BindingBuilder.bind(productUpdateQueue).to(directExchange).withQueueName();
    }
    @Bean
    public Binding bindingDirectExchangeProductDeleteQueue(Queue productDeleteQueue,DirectExchange directExchange){
        return BindingBuilder.bind(productDeleteQueue).to(directExchange).withQueueName();
    }
}
