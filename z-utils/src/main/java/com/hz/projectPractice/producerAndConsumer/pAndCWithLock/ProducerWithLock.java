package com.hz.projectPractice.producerAndConsumer.pAndCWithLock;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zehua
 * @date 2020/12/23 11:26
 */
public class ProducerWithLock implements Runnable {
    private Queue<String> queue;
    private int size;
    private ReentrantLock lock;
    private Condition takeCond;
    private Condition putCond;
    private int num;
    private int i = 0;

    public ProducerWithLock(Queue<String> queue, int size, ReentrantLock lock, Condition takeCond, Condition putCond, int num) {
        this.queue = queue;
        this.size = size;
        this.lock = lock;
        this.takeCond = takeCond;
        this.putCond = putCond;
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lock.lockInterruptibly();
                while (queue.size() >= size) {
                    putCond.await();
                }
                String item = num + "---" + (++i);
                queue.add(item);
                System.out.println(Thread.currentThread().getName() + "---producer: " + item);

                takeCond.signalAll();
                lock.unlock();

                Thread.sleep((int) (Math.random() * 1000));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
