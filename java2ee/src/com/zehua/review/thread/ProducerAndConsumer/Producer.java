package com.zehua.review.thread.ProducerAndConsumer;

import java.util.Queue;

/**
 * @Auther zehua
 * @Date 2020/10/30 14:18
 */
public class Producer implements Runnable {
    private Queue<Integer> queue;
    private int size;

    public Producer() {

    }

    public Producer(Queue<Integer> queue, int size) {
        this.queue = queue;
        this.size = size;
    }

    @Override
    public void run() {
        int count = 1;
        while (true) {
            try {
                synchronized (queue) {
                    while (size <= this.queue.size()) {
                        queue.wait();
                    }
                    System.out.println(Thread.currentThread().getName() + ": produce " + (count));
                    queue.add(count++);
                    queue.notifyAll();
                }
                Thread.sleep((int) (Math.random() * 300));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
