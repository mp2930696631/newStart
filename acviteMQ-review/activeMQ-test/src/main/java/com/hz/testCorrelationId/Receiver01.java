package com.hz.testCorrelationId;

import com.hz.Utils;

import javax.jms.*;

/**
 * @author zehua
 * @date 2021/1/3 11:31
 */
public class Receiver01 {

    private Connection connection = null;

    private String name;

    private int rand = 0;

    public Receiver01() {
    }

    public Receiver01(String name) {
        this.name = name;
        rand = (int) ((Math.random() * 7) + 1);
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

    public static void main(String[] args) {
        Receiver01 receiver01 = new Receiver01("receiver00000");
        receiver01.receive();
    }

}
