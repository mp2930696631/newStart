package com.hz.projectPractice.producerAndConsumer.blockingQueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 自定义环形数组
 *
 * @author zehua
 * @date 2020/12/23 10:38
 */
public class MyBlockingQueue<V> implements IBlockingQueue<V> {
    private Object[] objs;
    private int size;
    private int itemSize = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition putCond = lock.newCondition();
    private Condition takeCond = lock.newCondition();

    private int putIndex = 0;
    private int takeIndex = 0;

    public MyBlockingQueue() {
        this.size = 32;
        this.objs = new Object[32];
    }

    public MyBlockingQueue(int size) {
        this.size = size;
        this.objs = new Object[size];
    }

    public void put(V v) throws InterruptedException {
        lock.lockInterruptibly();
        while (itemSize >= this.size) {
            putCond.await();
        }
        this.objs[putIndex] = v;
        if (++putIndex == size) putIndex = 0;
        itemSize++;
        System.out.println(Thread.currentThread().getName() + "---producer: " + v);

        takeCond.signalAll();
        lock.unlock();
    }

    public V take() throws InterruptedException {
        lock.lockInterruptibly();
        while (itemSize <= 0) {
            takeCond.await();
        }


        final V v = (V) objs[takeIndex];
        if (++takeIndex >= size) takeIndex = 0;
        itemSize--;
        System.out.println(Thread.currentThread().getName() + "---consumer: " + v);

        putCond.signalAll();
        lock.unlock();

        return v;
    }
}
