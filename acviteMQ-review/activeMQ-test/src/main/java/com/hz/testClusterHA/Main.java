package com.hz.testClusterHA;

/**
 * @author zehua
 * @date 2021/1/3 12:17
 */
public class Main {

    public static void main(String[] args) {
        new Thread(() -> {
            Sender sender = new Sender();
            sender.send01();
        }).start();

        for (int i = 0; i < 3; i++) {
            Receiver receiver = new Receiver("receiver0" + (i + 1));
            receiver.receive();
        }
    }

}
