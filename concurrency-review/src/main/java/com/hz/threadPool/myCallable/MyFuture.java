package com.hz.threadPool.myCallable;

/**
 * @author zehua
 * @date 2020/11/28 14:40
 */
public interface MyFuture<V> {
    V get();
}
