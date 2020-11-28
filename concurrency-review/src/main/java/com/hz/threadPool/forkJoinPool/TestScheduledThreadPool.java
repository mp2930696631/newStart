package com.hz.threadPool.forkJoinPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author zehua
 * @date 2020/11/28 20:11
 */
public class TestScheduledThreadPool {

    public static void main(String[] args) {
        final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        for (int i = 0; i < 8; i++) {
            scheduledExecutorService.schedule(() -> {
                System.out.println(Thread.currentThread().getName());
            }, 1000 * i, TimeUnit.MILLISECONDS);
        }
    }

}
