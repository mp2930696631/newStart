package com.zehua.review.thread.producerAndConsumerByBlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Auther zehua
 * @Date 2020/10/30 14:53
 */
public class Main {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(5);
        Producer producer = new Producer(queue);
        Consumer consumerA = new Consumer(queue);
        Consumer consumerB = new Consumer(queue);
        Thread t1 = new Thread(producer, "producer");
        Thread t2 = new Thread(consumerA, "consumerA");
        Thread t3 = new Thread(consumerB, "consumerB");
        t1.start();
        t2.start();
        t3.start();
    }

}
