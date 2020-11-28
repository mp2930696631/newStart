package com.hz.threadPool.threadPoolExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zehua
 * @date 2020/11/28 15:14
 */
public class Main {
    private int a = 1;

    // 使用默认
    /*public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory());
        for (int i = 0;i<7;i++){
            tpe.execute(()->{
                System.out.println(Thread.currentThread().getName() + "-----start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-----end");
            });
        }
    }*/

    // 自定义ThreadFactory
    /*public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), new MyThreadFactory());
        for (int i = 0;i<7;i++){
            tpe.execute(()->{
                System.out.println(Thread.currentThread().getName() + "-----start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-----end");
            });
        }
    }*/

    // 自定义拒绝策略
    public static void main(String[] args) {
        ThreadPoolExecutor tpe = new ThreadPoolExecutor(0, 5, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2), Executors.defaultThreadFactory(), new MyRejectionStrategy());
        for (int i = 0; i < 10; i++) {
            tpe.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "-----start");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "-----end");
            });
        }
    }

}
