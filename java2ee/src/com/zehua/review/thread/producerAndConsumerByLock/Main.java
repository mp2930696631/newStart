package com.zehua.review.thread.producerAndConsumerByLock;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther zehua
 * @Date 2020/10/30 15:02
 */
public class Main {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition conCondi = lock.newCondition();
        Condition proCondi = lock.newCondition();
        Queue<Integer> queue = new LinkedList<>();
        Producer producer = new Producer(queue, 5, lock, proCondi, conCondi);
        Consumer consumerA = new Consumer(queue, lock, proCondi, conCondi);
        Consumer consumerB = new Consumer(queue, lock, proCondi, conCondi);
        Thread t1 = new Thread(producer, "producer");
        Thread t2 = new Thread(consumerA, "consumerA");
        Thread t3 = new Thread(consumerB, "consumerB");
        t1.start();
        t2.start();
        t3.start();
    }

}
