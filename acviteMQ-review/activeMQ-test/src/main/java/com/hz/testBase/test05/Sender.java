package com.hz.testBase.test05;

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
                final TextMessage textMessage = session.createTextMessage("hello activemq.........." + i);
                producer.send(textMessage);

                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
