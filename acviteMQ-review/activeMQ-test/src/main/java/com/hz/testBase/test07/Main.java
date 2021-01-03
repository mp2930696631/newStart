package com.hz.testBase.test07;

/**
 * 测试优先级
 *
 * @author zehua
 * @date 2021/1/3 8:38
 */
public class Main {

    public static void main(String[] args) {
        new Thread(() -> {
            Sender sender = new Sender();
            sender.send01();
        }).start();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //for (int i = 0; i < 3; i++) {
            Receiver receiver = new Receiver("receiver01");
            receiver.receive();
        //}

        /*Receiver receiver01_1 = new Receiver();
        Receiver receiver01_2 = new Receiver();
        Receiver receiver01_3 = new Receiver();
        receiver01_1.receive();
        receiver01_2.receive();
        receiver01_3.receive();*/
    }

}
