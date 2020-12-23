package com.hz.projectPractice.producerAndConsumer.pAndCWithLock;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zehua
 * @date 2020/12/23 11:26
 */
public class ConsumerWithLock implements Runnable {
    private Queue<String> queue;
    private int size;
    private ReentrantLock lock;
    private Condition takeCond;
    private Condition putCond;

    public ConsumerWithLock(Queue<String> queue, int size, ReentrantLock lock, Condition takeCond, Condition putCond) {
        this.queue = queue;
        this.size = size;
        this.lock = lock;
        this.takeCond = takeCond;
        this.putCond = putCond;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lockInterruptibly();
                while (queue.size() <= 0) {
                    takeCond.await();
                }
                final String item = queue.poll();
                System.out.println(Thread.currentThread().getName() + "---consumer: " + item);

                putCond.signalAll();
                lock.unlock();
                Thread.sleep((int) (Math.random() * 2000));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
