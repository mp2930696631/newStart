package com.hz.testBase.test07;

import com.hz.Utils;

import javax.jms.*;

/**
 * @author zehua
 * @date 2021/1/3 8:39
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

            for (int i = 0; i < 100; i++) {
                TextMessage textMessage = session.createTextMessage("hello activemq.........." + i);
                if (i % 3 == 0) {
                    producer.send(textMessage, DeliveryMode.PERSISTENT, 8, 60 * 1000 * 8);
                    continue;
                }
                producer.send(textMessage);

                // Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
