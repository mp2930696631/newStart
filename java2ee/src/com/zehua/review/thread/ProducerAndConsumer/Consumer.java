package com.zehua.review.thread.ProducerAndConsumer;

import java.util.Queue;

/**
 * @Auther zehua
 * @Date 2020/10/30 14:18
 */
public class Consumer implements Runnable {
    private Queue<Integer> queue;

    public Consumer() {
    }

    public Consumer(Queue<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        queue.wait();
                    }
                    int count = queue.poll();
                    System.out.println(Thread.currentThread().getName() + ": consume " + count);
                    queue.notifyAll();
                }

                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
