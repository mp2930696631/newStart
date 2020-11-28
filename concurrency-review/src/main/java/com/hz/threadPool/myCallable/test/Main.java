package com.hz.threadPool.myCallable.test;

import com.hz.threadPool.myCallable.MyExecutor;
import com.hz.threadPool.myCallable.MyFuture;

/**
 * @author zehua
 * @date 2020/11/28 15:02
 */
public class Main {

    public static void main(String[] args) {
        TestMyCallable testMyCallable = new TestMyCallable();
        MyExecutor<Integer> executor = new MyExecutor<>();
        final MyFuture<Integer> myFuture = executor.executor(testMyCallable);
        System.out.println(myFuture.get());
    }


}
