package com.zehua.review.thread.threadPool;

import java.util.concurrent.*;

/**
 * 1、自定义ThreadPool
 * 2、cached
 * 3、fixed
 * 4、single
 * 主要注意myThreadPool,可以看该方法的说明（自定义ThreadPoolExecutor）
 * @Auther zehua
 * @Date 2020/10/30 15:27
 */
public class TestThreadPool {

    public static void main(String[] args) {
        // threadPoolExecutor_cached();

        // threadPoolExecutor_fixed();

        // threadPoolExecutor_single();

        myThreadPool();
    }

    private static void threadPoolExecutor(ExecutorService executorService) {
        for (int i = 0; i < 10; i++) {
            Runnable runnable = () -> {
                System.out.println(Thread.currentThread().getName() + ": execute");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService.execute(runnable);
        }
    }

    public static void threadPoolExecutor_cached() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        threadPoolExecutor(executorService);
    }

    public static void threadPoolExecutor_fixed() {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        threadPoolExecutor(executorService);
    }

    public static void threadPoolExecutor_single() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        threadPoolExecutor(executorService);
    }

    /**
     * 为了测试自定义的ThreadPool
     * 1、当ArrayBlockingQueue容量为5的时候，可以检验线程池的执行逻辑
     * 2、当ArrayBlockingQueue容量为2（减小它的容量）的时候，可以检验线程池的拒绝策略
     */
    public static void myThreadPool() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, 5, 60L,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(2)
        );

        for (int i = 1; i <= 10; i++) {
            executor.execute(new Th(i));
            try {
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static class Th implements Runnable {
        private int i;

        public Th() {
        }

        public Th(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + ": execute " + i);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
