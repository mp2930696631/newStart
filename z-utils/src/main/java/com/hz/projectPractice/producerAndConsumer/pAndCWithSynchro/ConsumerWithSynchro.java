package com.hz.projectPractice.producerAndConsumer.pAndCWithSynchro;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zehua
 * @date 2020/12/23 11:26
 */
public class ConsumerWithSynchro implements Runnable {
    private Queue<String> queue;
    private int size;
    private Object lockObj;

    public ConsumerWithSynchro(Queue<String> queue, int size, Object lockObj) {
        this.queue = queue;
        this.size = size;
        this.lockObj = lockObj;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lockObj) {
                    while (queue.size() <= 0) {
                        lockObj.wait();
                    }
                    final String item = queue.poll();
                    System.out.println(Thread.currentThread().getName() + "---consumer: " + item);

                    lockObj.notifyAll();
                }
                Thread.sleep((int) (Math.random() * 2000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
