package com.hz.testReplyTo;

import com.hz.Utils;

import javax.jms.*;

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
            final MessageConsumer consumer = session.createConsumer(userQueue);
            final Message message = consumer.receive();
            Destination jmsReplyTo = null;
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                try {
                    System.out.println(name + "----------------" + tm.getText());
                    jmsReplyTo = message.getJMSReplyTo();
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }

            final MessageProducer producer = session.createProducer(jmsReplyTo);
            for (int i = 0; i < 100; i++) {
                final TextMessage textMessage = session.createTextMessage(name + ": " + "hello producer.........." + i);
                producer.send(textMessage);

                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
