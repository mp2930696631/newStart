package com.hz.testClusterHP;

import com.hz.Utils;
import com.hz.UtilsHP;

import javax.jms.*;

/**
 * @author zehua
 * @date 2021/1/3 8:39
 */
public class Sender {
    private Connection connection = null;

    public void init() {
        this.connection = UtilsHP.getConn();
    }

    public void send01() {
        init();
        try {
            final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final Topic userTopic = session.createTopic("userTopic");
            final MessageProducer producer = session.createProducer(userTopic);

            for (int i = 0; i < 200; i++) {
                final TextMessage textMessage = session.createTextMessage("hello activemq.........." + i);
                producer.send(textMessage);

                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
