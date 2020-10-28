package com.zehua.testThread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 生产者消费者模型，通过synchronized关键字来实现
 * 一个生产者
 * 两个消费者
 */
public class ProducerAndConsumer {

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedList<>();
        int length = 5;

        Thread threadPro = new Thread(new Producer(queue, length), "producer thread");
        Thread threadConA = new Thread(new Consumer(queue, length), "consumer A thread");
        Thread threadConB = new Thread(new Consumer(queue, length), "consumer B thread");
        threadPro.start();
        threadConA.start();
        threadConB.start();
    }

    public static class Producer implements Runnable {
        private Queue<Integer> queue;
        private int length;

        public Producer(Queue<Integer> queue, int length) {
            this.queue = queue;
            this.length = length;
        }

        @Override
        public void run() {
            int i = 1;
            while (true) {
                synchronized (queue) {
                    while (queue.size() >= length) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    queue.add(i);
                    System.out.println(Thread.currentThread().getName() + ": product--" + (i++));
                    queue.notifyAll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    public static class Consumer implements Runnable {
        private Queue<Integer> queue;
        private int length;

        public Consumer(Queue<Integer> queue, int length) {
            this.queue = queue;
            this.length = length;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int i = queue.poll();
                    System.out.println(Thread.currentThread().getName() + ":consume----" + i);
                    queue.notifyAll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


}
