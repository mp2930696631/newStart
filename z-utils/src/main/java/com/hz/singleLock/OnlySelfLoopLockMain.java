package com.hz.singleLock;

import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2021/1/28 8:29
 */
public class OnlySelfLoopLockMain {

    private static int n = 0;
    private static int size = 1000;
    private static CountDownLatch countDownLatch = new CountDownLatch(size);

    public static void main(String[] args) throws InterruptedException {

        OnlySelfLoopLock lock = new OnlySelfLoopLock();
        for (int i = 0; i < size; i++) {
            new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    lock.lock();
                    // 因为这个临界区很简单，所以，执行很快
                    n++;

                    // 如果加上下面的代码，计算机都要崩。。。。
                    /*for (int x = 0;x<100;x++){
                        System.out.print("");
                    }*/
                    lock.unlock();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();

        System.out.println(n);
    }
}