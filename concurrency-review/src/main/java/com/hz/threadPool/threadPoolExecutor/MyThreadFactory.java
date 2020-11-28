package com.hz.threadPool.threadPoolExecutor;

import java.util.concurrent.ThreadFactory;

/**
 * @author zehua
 * @date 2020/11/28 15:31
 */
public class MyThreadFactory implements ThreadFactory {
    int index = 0;

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, "zehua" + (++index));
    }
}
