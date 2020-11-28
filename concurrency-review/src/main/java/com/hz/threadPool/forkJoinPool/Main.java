package com.hz.threadPool.forkJoinPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zehua
 * @date 2020/11/28 16:17
 */
public class Main {
    static int numCount = 10000;
    static int[] nums = new int[numCount];

    static {
        for (int i = 0; i < numCount; i++) {
            nums[i] = i + 1;
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        MyTask task = new MyTask(0, numCount);
        forkJoinPool.execute(task);
        System.out.println(task.join());

        LockSupport.park();

        //Executors.newWorkStealingPool()
    }

    public static class MyTask extends RecursiveTask<Integer> {
        private int start;
        private int end;

        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        protected Integer compute() {
            System.out.println(Thread.currentThread().getName());
            if (end - start > 3000) {
                int mid = start + (end - start) / 2;
                MyTask t1 = new MyTask(start, mid);
                MyTask t2 = new MyTask(mid, end);
                t1.fork();
                t2.fork();

                return t1.join() + t2.join();
            } else {
                int sum = 0;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            }

        }
    }

}
