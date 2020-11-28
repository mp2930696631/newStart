package com.hz.container.collection.queue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @author zehua
 * @date 2020/11/28 9:36
 */
public class MyPriorityBlockingQueue {

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue priorityBlockingQueue = new PriorityBlockingQueue();
        priorityBlockingQueue.put(2);
        priorityBlockingQueue.put(1);
        System.out.println(priorityBlockingQueue.take());
    }

}
