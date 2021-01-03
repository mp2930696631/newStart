package com.hz.testClusterHP;

import com.hz.Utils;
import com.hz.UtilsHA;
import com.hz.UtilsHP;

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
        this.connection = UtilsHA.getConn();
    }

    public void receive() {
        init();
        try {
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final Topic userTopic = session.createTopic("userTopic");
            final MessageConsumer consumer = session.createConsumer(userTopic);
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
