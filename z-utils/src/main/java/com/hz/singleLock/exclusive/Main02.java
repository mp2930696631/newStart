package com.hz.singleLock.exclusive;

import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/24 11:47
 */
public class Main02 {
    private static int n = 0;
    private static int m = 0;
    private static int x = 0;
    private static int y = 0;
    private static int size = 1000;
    private static CountDownLatch countDownLatch = new CountDownLatch(size);

    public static void main(String[] args) throws InterruptedException {
        ReentrantExLock lock = new ReentrantExLock();
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    /*lock.lock();
                    n++;
                    lock.unlock();*/

                   /* n++;
                    m++;
                    x++;
                    y++;*/


                    lock.lock();
                    n++;
                    lock.lock();
                    m++;
                    lock.lock();
                    x++;
                    lock.unlock();

                    lock.unlock();

                    lock.lock();
                    y++;
                    lock.unlock();

                    lock.unlock();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();

        System.out.println("n = " + n);
        System.out.println("m = " + m);
        System.out.println("x = " + x);
        System.out.println("y = " + y);
    }

}
