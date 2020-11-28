package com.hz.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.Executors;

/**
 * @author zehua
 * @date 2020/11/27 17:33
 */
public class IntegerEventMain {

    public static void main(String[] args) throws InterruptedException {
        int size = 1024;
        Disruptor<IntegerEvent> disruptor = new Disruptor<>(new IntegerEventFactory(), size, Executors.defaultThreadFactory());
        disruptor.handleEventsWith(new IntegerEventHandler());
        disruptor.start();
        RingBuffer<IntegerEvent> ringBuffer = disruptor.getRingBuffer();
        while (true) {
            int num = (int) (Math.random() * 5000);
            ringBuffer.publishEvent((event, equence, data) -> event.setIntNum(data), num);
            Thread.sleep(1000);
        }
    }
}
