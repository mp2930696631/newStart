package com.hz.springbootActivemq.queue;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author zehua
 * @date 2021/1/3 6:43
 */
@Service
public class Receiver {

    @JmsListener(destination = "userQueue")
    public void receive(Message message) throws JMSException {
        if (message instanceof TextMessage) {
            System.out.println(((TextMessage) message).getText());
            System.out.println("test ok.....");
        }
        System.out.println(message.getClass());

    }

    @JmsListener(destination = "userQueue", containerFactory = "jmsListenerContainerQueue")
    public void receive02(Message message) throws JMSException {
        if (message instanceof MapMessage) {
            System.out.println(((MapMessage) message).getObject("name"));
            System.out.println("test ok.....");
        }
        System.out.println(message.getClass());
    }

    @JmsListener(destination = "userQueue", containerFactory = "jmsListenerContainerQueue")
    public void receive03(String msg) throws JMSException {
        System.out.println(msg);

    }

}
