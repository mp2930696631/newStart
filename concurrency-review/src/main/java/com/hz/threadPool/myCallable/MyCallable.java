package com.hz.threadPool.myCallable;

/**
 * @author zehua
 * @date 2020/11/28 14:36
 */
public interface MyCallable<V> {
    V call();
}
