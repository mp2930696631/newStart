package com.zehua.review.thread.producerAndConsumerByLock;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Auther zehua
 * @Date 2020/10/30 15:02
 */
public class Producer implements Runnable {
    private Queue<Integer> queue;
    private int size;

    private Lock lock;
    private Condition proCondi;
    private Condition conCondi;

    public Producer() {

    }

    public Producer(Queue<Integer> queue, int size, Lock lock, Condition proCondi, Condition conCondi) {
        this.queue = queue;
        this.size = size;
        this.lock = lock;
        this.proCondi = proCondi;
        this.conCondi = conCondi;
    }

    @Override
    public void run() {
        int count = 1;
        while (true) {
            try {
                lock.lockInterruptibly();
                while (size <= this.queue.size()) {
                    proCondi.await();
                }
                System.out.println(Thread.currentThread().getName() + ": produce " + (count));
                queue.add(count++);
                conCondi.signalAll();
                lock.unlock();

                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
