package com.hz.projectPractice.producerAndConsumer.blockingQueue;

/**
 * @author zehua
 * @date 2020/12/23 10:55
 */
public interface IBlockingQueue<V> {
    public void put(V v) throws InterruptedException;

    public V take() throws InterruptedException;
}
