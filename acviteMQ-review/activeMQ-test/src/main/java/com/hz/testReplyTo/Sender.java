package com.hz.testReplyTo;

import com.hz.Utils;

import javax.jms.*;

/**
 * @author zehua
 * @date 2021/1/3 9:49
 */
public class Sender {
    private Connection connection = null;

    public void init() {
        this.connection = Utils.getConn();
    }

    public void send01() {
        init();
        try {
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final Queue userQueue = session.createQueue("userQueue");
            final MessageProducer producer = session.createProducer(userQueue);
            final TextMessage textMessage = session.createTextMessage("hello consumer..........");
            final TemporaryQueue temporaryQueue = session.createTemporaryQueue();
            textMessage.setJMSReplyTo(temporaryQueue);
            producer.send(textMessage);

            final MessageConsumer consumer = session.createConsumer(temporaryQueue);
            consumer.setMessageListener(message -> {
                if (message instanceof TextMessage) {
                    TextMessage tm = (TextMessage) message;
                    try {
                        System.out.println(tm.getText());
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
