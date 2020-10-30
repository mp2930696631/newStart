package com.zehua.review.thread.producerAndConsumerByBlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * @Auther zehua
 * @Date 2020/10/30 14:53
 */
public class Producer implements Runnable {
    private BlockingQueue<Integer> blockingQueue;

    public Producer() {
    }

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        int count = 1;
        while (true) {
            try {
                System.out.println(Thread.currentThread().getName() + ": produce " + count);
                blockingQueue.put(count++);
                Thread.sleep((int) (Math.random() * 500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
