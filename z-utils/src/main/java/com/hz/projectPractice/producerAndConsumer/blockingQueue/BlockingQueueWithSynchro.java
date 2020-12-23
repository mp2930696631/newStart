package com.hz.projectPractice.producerAndConsumer.blockingQueue;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author zehua
 * @date 2020/12/23 10:21
 */
public class BlockingQueueWithSynchro<V> implements IBlockingQueue<V> {
    private Queue<V> queue = new ArrayDeque<>();
    private int size;
    private Object lockObj = new Object();

    public BlockingQueueWithSynchro() {
        this.size = Integer.MAX_VALUE;
    }

    public BlockingQueueWithSynchro(int size) {
        this.size = size;
    }

    public void put(V v) throws InterruptedException {
        synchronized (lockObj) {
            while (queue.size() >= this.size) {
                lockObj.wait();
            }
            this.queue.add(v);

            System.out.println(Thread.currentThread().getName() + "---producer: " + v);

            lockObj.notifyAll();
        }
    }

    public V take() throws InterruptedException {
        V v = null;
        synchronized (lockObj) {
            while (queue.size() <= 0) {
                lockObj.wait();
            }
            v = queue.poll();

            System.out.println(Thread.currentThread().getName() + "---consumer: " + v);

            lockObj.notifyAll();
        }

        return v;
    }
}
