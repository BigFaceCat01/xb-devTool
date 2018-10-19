package xb.dev.tools.mongodb.config;

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


    public static final String QUEUE_NEWS_INSERT = "mongo.queue.news.insert";
    public static final String QUEUE_NEWS_UPDATE = "mongo.queue.news.update";
    public static final String QUEUE_NEWS_DELETE = "mongo.queue.news.delete";
    public static final String DIRECT_EXCHANGE_NEWS_INSERT = "mongo.direct.exchange.news.insert";
    public static final String DIRECT_EXCHANGE_NEWS_UPDATE = "mongo.direct.exchange.news.update";
    public static final String DIRECT_EXCHANGE_NEWS_DELETE = "mongo.direct.exchange.news.delete";
    public static final String ROUTING_NEWS_INSERT = "mongo.routing.news.insert";
    public static final String ROUTING_NEWS_UPDATE = "mongo.routing.news.update";
    public static final String ROUTING_NEWS_DELETE = "mongo.routing.news.delete";



    @Bean
    public Queue newsInsertQueue() {
        return new Queue(QUEUE_NEWS_INSERT);
    }

    @Bean
    public Queue newsUpdateQueue() {
        return new Queue(QUEUE_NEWS_UPDATE);
    }

    @Bean
    public Queue newsDeleteQueue() {
        return new Queue(QUEUE_NEWS_DELETE);
    }

    @Bean
    public DirectExchange newsInsertDirectExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NEWS_INSERT);
    }

    @Bean
    public DirectExchange newsUpdateDirectExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NEWS_UPDATE);
    }

    @Bean
    public DirectExchange newsDeleteDirectExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NEWS_DELETE);
    }

    @Bean
    public Binding bindingDirectExchangeProductInsertQueue(Queue newsInsertQueue,DirectExchange newsInsertDirectExchange){
        return BindingBuilder.bind(newsInsertQueue).to(newsInsertDirectExchange).with(ROUTING_NEWS_INSERT);
    }
    @Bean
    public Binding bindingDirectExchangeProductUpdateQueue(Queue newsUpdateQueue,DirectExchange newsUpdateDirectExchange){
        return BindingBuilder.bind(newsUpdateQueue).to(newsUpdateDirectExchange).with(ROUTING_NEWS_UPDATE);
    }
    @Bean
    public Binding bindingDirectExchangeProductDeleteQueue(Queue newsDeleteQueue,DirectExchange newsDeleteDirectExchange){
        return BindingBuilder.bind(newsDeleteQueue).to(newsDeleteDirectExchange).with(ROUTING_NEWS_DELETE);
    }
}
