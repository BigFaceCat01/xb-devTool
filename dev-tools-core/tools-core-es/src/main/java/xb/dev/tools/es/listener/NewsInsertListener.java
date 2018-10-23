//package xb.dev.tools.es.listener;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Service;
//import xb.dev.tools.es.config.RabbitConfig;
//
///**
// * @author Created by huangxb
// * @date 2018-10-19 15:36:34
// */
//@Service
//@Slf4j
//@RabbitListener(queues = RabbitConfig.QUEUE_NEWS_INSERT)
//public class NewsInsertListener {
//    @RabbitHandler
//    public void onMessage(String message){
//        log.info("收到来自{}队列的消息:{}",RabbitConfig.QUEUE_NEWS_INSERT,message);
//    }
//}
