package com.hz.testCorrelationId;

import com.hz.Utils;

import javax.jms.*;
import java.util.UUID;

/**
 * @author zehua
 * @date 2021/1/3 9:49
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
            final Queue userQueue = session.createQueue("userQueue");
            MessageConsumer consumer = session.createConsumer(userQueue, "cUUID='1'");
            // 第一步，先收到producer的uuid
            final Message message = consumer.receive();
            String pUUID = null;
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                try {
                    System.out.println(name + "----------------" + tm.getText());
                    pUUID = tm.getStringProperty("pUUID");
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }

            // 第二步，在发送自己的uuid
            final MessageProducer producer = session.createProducer(userQueue);
            final TextMessage textMessage = session.createTextMessage(name + ": " + "hello producer..........");
            String cUUID = UUID.randomUUID().toString();
            textMessage.setStringProperty("cUUID", cUUID);
            textMessage.setStringProperty("pUUID", pUUID);
            producer.send(textMessage);

            // 第三步，接收消息
            MessageConsumer cons = session.createConsumer(userQueue, "pUUID='" + pUUID + "' and type='p'");
            cons.setMessageListener(m -> {
                System.out.println("====");
                if (m instanceof TextMessage) {
                    TextMessage tm = (TextMessage) m;
                    try {
                        System.out.println(tm.getText());
                        System.out.println(tm.getStringProperty("cUUID") + "-------------" + cUUID);
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
