package xb.dev.tools.mongodb.config;//package xb.dev.tools.config;
//
//import org.springframework.amqp.core.*;
//import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author: Created by huangxb on 2018-08-03 17:13:48
// * @Description: 消息配置
// */
//@Configuration
//public class RabbitConfig {
//    public static final String NEWS_SEARCH_QUEUE = "newsSearchQueue";
//    public static final String ORDER_QUEUE = "orderQueue";
//    public static final String NEWS_INSERT_QUEUE = "newsInsertQueue";
//    public static final String NEWS_UPDATE_QUEUE = "newsUpdateQueue";
//    public static final String NEWS_DELETE_QUEUE = "newsDeleteQueue";
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }
//    @Bean
//    public Queue productQueue(){
//        return new Queue(NEWS_SEARCH_QUEUE);
//    }
//    @Bean
//    public Queue orderQueue(){
//        return new Queue(ORDER_QUEUE);
//    }
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange("orderFanoutExchange");
//    }
//    @Bean
//    public Binding bindingQueue(Queue orderQueue, FanoutExchange fanoutExchange){
//        return BindingBuilder.bind(orderQueue).to(fanoutExchange);
//    }
//
//    @Bean
//    public Queue newsInsertQueue() {
//        return new Queue(NEWS_INSERT_QUEUE);
//    }
//
//    @Bean
//    public Queue newsUpdateQueue() {
//        return new Queue(NEWS_UPDATE_QUEUE);
//    }
//
//    @Bean
//    public Queue newsDeleteQueue() {
//        return new Queue(NEWS_DELETE_QUEUE);
//    }
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange("newsDirectExchange");
//    }
//
//    @Bean
//    public Binding bindingDirectExchangeProductInsertQueue(Queue newsInsertQueue,DirectExchange directExchange){
//        return BindingBuilder.bind(newsInsertQueue).to(directExchange).withQueueName();
//    }
//    @Bean
//    public Binding bindingDirectExchangeProductUpdateQueue(Queue newsUpdateQueue,DirectExchange directExchange){
//        return BindingBuilder.bind(newsUpdateQueue).to(directExchange).withQueueName();
//    }
//    @Bean
//    public Binding bindingDirectExchangeProductDeleteQueue(Queue newsDeleteQueue,DirectExchange directExchange){
//        return BindingBuilder.bind(newsDeleteQueue).to(directExchange).withQueueName();
//    }
//}
