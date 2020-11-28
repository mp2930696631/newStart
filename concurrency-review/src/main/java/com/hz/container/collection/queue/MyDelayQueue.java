package com.hz.container.collection.queue;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zehua
 * @date 2020/11/27 21:38
 */
public class MyDelayQueue {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayTask> delayQueue = new DelayQueue();
        delayQueue.offer(new DelayTask(1000));
        delayQueue.offer(new DelayTask(2000));
        delayQueue.offer(new DelayTask(5000));
        delayQueue.offer(new DelayTask(3000));


        while (!delayQueue.isEmpty()) {
            final Delayed poll = delayQueue.take();
            System.out.println(poll);
        }
    }

    public static class DelayTask implements Delayed {
        private long timeDelay;
        private long timstamp = System.currentTimeMillis();

        public DelayTask(long timeDelay) {
            this.timeDelay = timeDelay;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return (timeDelay + timstamp) - System.currentTimeMillis();
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(null) - o.getDelay(null));
        }

        @Override
        public String toString() {
            return "time===" + timeDelay;
        }
    }

}
