package com.hz.testCorrelationId;

/**
 * 不行，必须使用两个uuid才能达到效果，而不是使用CorrelationId
 *
 * @author zehua
 * @date 2021/1/3 9:49
 */
public class Main {

    public static void main(String[] args) {
        new Thread(() -> {
            Sender sender = new Sender();
            sender.send01();
        }).start();

        for (int i = 0; i < 1; i++) {
            Receiver receiver = new Receiver("receiver0" + (i + 1));
            receiver.receive();
        }
    }

}
