package com.hz.container.collection.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @author zehua
 * @date 2020/11/28 9:49
 */
public class MySynchronousQueue {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue synchronousQueue = new SynchronousQueue();
        new Thread(() -> {
            try {
                System.out.println(synchronousQueue.take());
                Thread.sleep(1000);
                System.out.println(synchronousQueue.take());
            } catch (Exception e) {
                System.out.println("exception");
            }
        }).start();
        synchronousQueue.put("bbbb");
        synchronousQueue.put("ccc");

    }

}
