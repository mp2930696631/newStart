package com.hz.threadPool.threadPoolExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zehua
 * @date 2020/11/28 15:34
 */
public class MyRejectionStrategy implements RejectedExecutionHandler {
    int index = 0;

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        /*System.out.println("另起线程执行");
        new Thread(r, "other" + (++index)).start();*/
        System.out.println("queue:====" + executor.getQueue().size());
    }
}
