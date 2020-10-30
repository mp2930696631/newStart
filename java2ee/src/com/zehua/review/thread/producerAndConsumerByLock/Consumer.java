package com.zehua.review.thread.producerAndConsumerByLock;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Auther zehua
 * @Date 2020/10/30 15:03
 */
public class Consumer implements Runnable {
    private Queue<Integer> queue;

    private Lock lock;
    private Condition proCondi;
    private Condition conCondi;

    public Consumer() {
    }

    public Consumer(Queue<Integer> queue, Lock lock, Condition proCondi, Condition conCondi) {
        this.queue = queue;
        this.lock = lock;
        this.proCondi = proCondi;
        this.conCondi = conCondi;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lockInterruptibly();
                while (queue.isEmpty()) {
                    conCondi.await();
                }
                int count = queue.poll();
                System.out.println(Thread.currentThread().getName() + ": consume " + count);
                proCondi.signal();
                lock.unlock();

                Thread.sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
