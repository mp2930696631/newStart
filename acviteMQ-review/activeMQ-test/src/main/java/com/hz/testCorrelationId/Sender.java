package com.hz.testCorrelationId;

import com.hz.Utils;

import javax.jms.*;
import java.util.UUID;

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
            TextMessage textMessage = session.createTextMessage("hello consumer..........");
            // 第一步，先将自己的uuid传送至consumer
            String pUUID = UUID.randomUUID().toString();
            textMessage.setStringProperty("pUUID", pUUID);
            textMessage.setStringProperty("cUUID", 1 + "");
            producer.send(textMessage);

            // 第二步，接收到consumer传送的uuid
            /*String cUUID = null;
            final MessageConsumer consumer = session.createConsumer(userQueue, "pUUID='" + pUUID + "'");
            final Message message = consumer.receive();
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                try {
                    System.out.println(tm.getText());
                    cUUID = tm.getStringProperty("cUUID");
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }*/

            //第三步，通信
            for (int i = 0; i < 100; i++) {
                TextMessage tMessage = session.createTextMessage("consumer ......continue" + i);
                tMessage.setStringProperty("pUUID", pUUID);
                //tMessage.setStringProperty("cUUID", cUUID);
                tMessage.setStringProperty("type", "p");
                producer.send(tMessage);

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
