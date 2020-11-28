package com.hz.threadPool.myCallable.test;

import com.hz.threadPool.myCallable.MyCallable;

/**
 * @author zehua
 * @date 2020/11/28 14:59
 */
public class TestMyCallable implements MyCallable<Integer> {
    @Override
    public Integer call() {
        int sum = 0;
        for (int i = 1; i <= 1000; i++) {
            sum += i;
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }
}
