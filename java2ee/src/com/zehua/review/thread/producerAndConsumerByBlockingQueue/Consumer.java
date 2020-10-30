package com.zehua.review.thread.producerAndConsumerByBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Auther zehua
 * @Date 2020/10/30 14:53
 */
public class Consumer implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public Consumer() {
    }

    public Consumer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int count = this.blockingQueue.take();
                System.out.println(Thread.currentThread().getName() + ": consume " + count);
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
