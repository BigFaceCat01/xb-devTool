package xb.dev.tools.tool.rabbit.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xb.dev.tools.constant.QueueConstant;
import xb.dev.tools.exception.XbServiceException;
import xb.dev.tools.tool.rabbit.service.MessageService;

/**
 * @Author: Created by huangxb on 2018-08-03 17:23:43
 * @Description:
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public Object sendMessage(String msg, String queue) throws XbServiceException {
        amqpTemplate.convertAndSend(queue,msg);
        return null;
    }

    @Override
    public Object receiveMessage(String queueName, String exchangeName) throws XbServiceException {
        return null;
    }
}
