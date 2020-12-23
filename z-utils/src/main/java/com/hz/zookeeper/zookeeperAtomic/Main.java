package com.hz.zookeeper.zookeeperAtomic;

import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/23 13:27
 */
public class Main {
    private static int count = 0;

    public static void main(String[] args) {
        int size = 5;

        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                ZookeeperAtomic za = new ZookeeperAtomic();
                for (int j = 0; j < 1000; j++) {
                    count = za.getAndIncrement();
                }
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(count);

    }

}
