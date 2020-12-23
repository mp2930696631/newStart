package com.hz.projectPractice.producerAndConsumer.pAndCWithSynchro;

import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zehua
 * @date 2020/12/23 11:26
 */
public class ProducerWithSynchro implements Runnable {
    private Queue<String> queue;
    private int size;
    private int num;
    private int i = 0;
    private Object lockObj;

    public ProducerWithSynchro(Queue<String> queue, int size, Object lockObj, int num) {
        this.queue = queue;
        this.size = size;
        this.lockObj = lockObj;
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lockObj) {
                    while (queue.size() >= size) {
                        lockObj.wait();
                    }
                    String item = num + "---" + (++i);
                    queue.add(item);
                    System.out.println(Thread.currentThread().getName() + "---producer: " + item);

                    lockObj.notifyAll();
                }
                Thread.sleep((int) (Math.random() * 1000));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
