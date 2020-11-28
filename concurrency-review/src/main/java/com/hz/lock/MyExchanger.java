package com.hz.lock;

import java.util.concurrent.Exchanger;

/**
 * @author zehua
 * @date 2020/11/28 10:38
 */
public class MyExchanger {

    public static void main(String[] args) throws InterruptedException {
        Exchanger exchanger = new Exchanger();
        new Thread(()->{
            try {
                System.out.println(exchanger.exchange("bcd"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println(exchanger.exchange("abc"));
    }

}
