package com.hz.springbootActivemq.queue;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zehua
 * @date 2021/1/3 6:43
 */
@Service
public class Sender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send01() {
        jmsMessagingTemplate.convertAndSend("userQueue", "hello activemq");
    }

    public void send02() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "zehua");
        jmsMessagingTemplate.convertAndSend("userQueue", map);
    }

    public void send03() {
        List<String> list = new ArrayList<>();
        list.add("zehua");
        list.add("huazai");
        jmsMessagingTemplate.convertAndSend("userQueue", list);
    }

}
