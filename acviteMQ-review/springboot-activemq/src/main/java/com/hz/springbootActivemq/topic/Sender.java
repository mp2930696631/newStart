package com.hz.springbootActivemq.topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author zehua
 * @date 2021/1/3 7:10
 */
// @Service
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send01() {

        final ActiveMQTopic activeMQTopic = new ActiveMQTopic("userTopic");

        jmsTemplate.send(activeMQTopic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("send02.........");

            }
        });
    }


}
