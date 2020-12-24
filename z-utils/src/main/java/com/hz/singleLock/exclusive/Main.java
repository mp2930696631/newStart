package com.hz.singleLock.exclusive;

import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/24 7:56
 */
public class Main {

    private static int n = 0;
    private static int size = 1000;
    private static CountDownLatch countDownLatch = new CountDownLatch(size);

    public static void main(String[] args) throws InterruptedException {
        // ExclusiveLock01 lock = new ExclusiveLock01();
        ExclusiveLock lock = new ExclusiveLock();
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    lock.lock();
                    n++;
                    lock.unlock();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();

        System.out.println(n);
    }

}
