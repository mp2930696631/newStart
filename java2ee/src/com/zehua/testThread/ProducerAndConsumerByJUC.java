package com.zehua.testThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerAndConsumerByJUC {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(5);
        Thread threadPro = new Thread(new Producer(blockingQueue), "producer thread");
        Thread threadConA = new Thread(new Consumer(blockingQueue), "consumer A thread");
        Thread threadConB = new Thread(new Consumer(blockingQueue), "consumer B thread");
        threadPro.start();
        threadConA.start();
        threadConB.start();
    }

    public static class Producer implements Runnable {
        private BlockingQueue<Integer> blockingQueue;

        public Producer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            int i = 1;
            while (true) {
                try {
                    // 注意，这句话的打印需放在put方法上面，因为，put后，就会唤醒消费者线程，可能造成顺序错乱
                    System.out.println(Thread.currentThread().getName() + ": product--" + (i));
                    blockingQueue.put(i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static class Consumer implements Runnable {
        private BlockingQueue<Integer> blockingQueue;

        public Consumer(BlockingQueue<Integer> blockingQueue) {
            this.blockingQueue = blockingQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int i = blockingQueue.take();
                    System.out.println(Thread.currentThread().getName() + ":consume----" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
