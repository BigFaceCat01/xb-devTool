package xb.dev.tools.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.config.RabbitConfig;
import xb.dev.tools.dao.entity.NewsEntity;
import java.util.List;

/**
 * @Author: Created by huangxb on 2018-08-14 18:05:07
 * @Description:
 */
@Service
public class NewsMqSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNewsInsert(NewsEntity newsEntity){
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange("newsDirectExchange");
        rabbitTemplate.convertAndSend(RabbitConfig.NEWS_INSERT_QUEUE,newsEntity);
    }

    public void sendNewsUpdate(NewsEntity newsEntity){
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange("newsDirectExchange");
        rabbitTemplate.convertAndSend(RabbitConfig.NEWS_UPDATE_QUEUE,newsEntity);
    }

    public void sendNewsDelete(List<String> ids){
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange("newsDirectExchange");
        rabbitTemplate.convertAndSend(RabbitConfig.NEWS_DELETE_QUEUE,ids);
    }
}
