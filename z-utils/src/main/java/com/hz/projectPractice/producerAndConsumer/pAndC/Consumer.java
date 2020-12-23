package com.hz.projectPractice.producerAndConsumer.pAndC;

import com.hz.projectPractice.producerAndConsumer.blockingQueue.IBlockingQueue;

/**
 * @author zehua
 * @date 2020/12/23 10:52
 */
public class Consumer implements Runnable {
    IBlockingQueue<String> blockingQueue;

    public Consumer(IBlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                final String item = blockingQueue.take();
                Thread.sleep((int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
