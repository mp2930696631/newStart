package com.hz.singleLock;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用VarHandle需要jdk1.9
 *
 * @author zehua
 * @date 2020/12/24 6:25
 */
public class LockUtils {
    private static int n = 0;
    private static int size = 100;
    private static CountDownLatch countDownLatch = new CountDownLatch(size);

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
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
