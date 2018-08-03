package xb.dev.tools.tool.rabbit.service;

import xb.dev.tools.exception.XbServiceException;

/**
 * @Author: Created by huangxb on 2018-08-03 17:21:17
 * @Description:
 */
public interface MessageService {
    /**
     * 发送消息
     * @param msg
     * @param queue
     * @return
     * @throws XbServiceException
     */
    Object sendMessage(String msg,String queue) throws XbServiceException;

    /**
     * 接收消息
     * @param queueName
     * @param exchangeName
     * @return
     * @throws XbServiceException
     */
    Object receiveMessage(String queueName,String exchangeName) throws XbServiceException;
}
