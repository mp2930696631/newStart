package com.hz.threadPool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author zehua
 * @date 2020/11/28 14:17
 */
public class TestFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            System.out.println("start to call.....");
            int sum = 0;
            for (int i = 1; i <= 1000; i++) {
                sum += i;
                Thread.sleep(5);
            }
            return sum;
        });

        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }

}
