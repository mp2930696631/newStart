package com.hz.projectPractice.producerAndConsumer.blockingQueue;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zehua
 * @date 2020/12/23 10:21
 */
public class BlockingQueueWithReenterLock<V> implements IBlockingQueue<V> {
    private Queue<V> queue = new ArrayDeque<>();
    private int size;
    private ReentrantLock lock = new ReentrantLock();
    private Condition putCond = lock.newCondition();
    private Condition takeCond = lock.newCondition();

    public BlockingQueueWithReenterLock() {
        this.size = Integer.MAX_VALUE;
    }

    public BlockingQueueWithReenterLock(int size) {
        this.size = size;
    }

    public void put(V v) throws InterruptedException {
        lock.lockInterruptibly();
        while (queue.size() >= this.size) {
            putCond.await();
        }
        this.queue.add(v);

        System.out.println(Thread.currentThread().getName() + "---producer: " + v);

        takeCond.signalAll();
        lock.unlock();
    }

    public V take() throws InterruptedException {
        lock.lockInterruptibly();
        while (queue.size() <= 0) {
            takeCond.await();
        }
        final V v = queue.poll();
        System.out.println(Thread.currentThread().getName() + "---consumer: " + v);

        putCond.signalAll();
        lock.unlock();

        return v;
    }

}
