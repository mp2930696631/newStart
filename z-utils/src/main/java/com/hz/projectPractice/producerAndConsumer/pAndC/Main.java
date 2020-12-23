package com.hz.projectPractice.producerAndConsumer.pAndC;

import com.hz.projectPractice.producerAndConsumer.blockingQueue.MyBlockingQueue;
import com.hz.projectPractice.producerAndConsumer.pAndC.Consumer;
import com.hz.projectPractice.producerAndConsumer.pAndC.Producer;

/**
 * @author zehua
 * @date 2020/12/23 11:11
 */
public class Main {

    public static void main(String[] args) {
        MyBlockingQueue<String> blockingQueue = new MyBlockingQueue<>(5);
        // BlockingQueueWithReenterLock<String> blockingQueue = new BlockingQueueWithReenterLock<>(5);
        // BlockingQueueWithSynchro<String> blockingQueue = new BlockingQueueWithSynchro<>(5);
        for (int i = 0; i < 2; i++) {
            new Thread(new Producer(blockingQueue, i + 1), "producer " + (i + 1)).start();
        }

        for (int i = 0; i < 4; i++) {
            new Thread(new Consumer(blockingQueue), "consumer " + (i + 1)).start();
        }
    }

}
