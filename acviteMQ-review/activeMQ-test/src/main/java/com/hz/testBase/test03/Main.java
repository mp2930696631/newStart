package com.hz.testBase.test03;

/**
 * @author zehua
 * @date 2021/1/3 8:38
 */
public class Main {

    public static void main(String[] args) {
        new Thread(() -> {
            Sender sender = new Sender();
            sender.send01();
        }).start();

        /*for (int i = 0; i < 3; i++) {
            Receiver receiver = new Receiver("receiver0" + (i + 1));
            receiver.receive();
        }*/

        /*Receiver receiver01_1 = new Receiver();
        Receiver receiver01_2 = new Receiver();
        Receiver receiver01_3 = new Receiver();
        receiver01_1.receive();
        receiver01_2.receive();
        receiver01_3.receive();*/
    }

}
