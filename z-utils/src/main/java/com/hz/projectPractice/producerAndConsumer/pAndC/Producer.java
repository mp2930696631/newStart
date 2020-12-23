package com.hz.projectPractice.producerAndConsumer.pAndC;

import com.hz.projectPractice.producerAndConsumer.blockingQueue.IBlockingQueue;

/**
 * @author zehua
 * @date 2020/12/23 10:52
 */
public class Producer implements Runnable {
    private IBlockingQueue<String> blockingQueue;
    private int num;
    private int i;

    public Producer(IBlockingQueue<String> blockingQueue, int num) {
        this.blockingQueue = blockingQueue;
        this.num = num;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String str = num + "---" + (++i);
                blockingQueue.put(str);
                Thread.sleep((int) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
