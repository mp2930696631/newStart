package com.hz.testBase.test04;

import com.hz.Utils;

import javax.jms.*;

/**
 * @author zehua
 * @date 2021/1/3 8:39
 */
public class Receiver {
    private Connection connection = null;

    private String name;

    public Receiver() {
    }

    public Receiver(String name) {
        this.name = name;
    }

    public void init() {
        this.connection = Utils.getConn();
    }

    public void receive() {
        init();
        try {
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final Queue userQueue = session.createQueue("userQueue?consumer.exclusive=true");
            final MessageConsumer consumer = session.createConsumer(userQueue);
            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    TextMessage tm = (TextMessage) message;
                    try {
                        System.out.println(name + "----------------" + tm.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
