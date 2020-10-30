package com.zehua.review.thread.ProducerAndConsumer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther zehua
 * @Date 2020/10/30 14:19
 */
public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        Producer producer = new Producer(queue, 5);
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
