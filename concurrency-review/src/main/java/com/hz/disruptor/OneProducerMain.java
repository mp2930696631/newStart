package com.hz.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * @author zehua
 * @date 2020/11/27 17:56
 */
public class OneProducerMain {

    public static IntegerEvent newInstance() {
        return new IntegerEvent();
    }

    public static void onEvent(IntegerEvent integerEvent, long l, boolean b) {
        System.out.println(Thread.currentThread().getName() + "----" + integerEvent.toString() + " sequence: " + l + " endOfBatch: " + b);
    }

    public static void main(String[] args) throws InterruptedException {
        int bufferSize = 1024;
        Disruptor<IntegerEvent> disruptor = new Disruptor<IntegerEvent>(OneProducerMain::newInstance,
                bufferSize,
                DaemonThreadFactory.INSTANCE, ProducerType.SINGLE, new BlockingWaitStrategy());
        disruptor.handleEventsWith(OneProducerMain::onEvent);
        disruptor.start();

        RingBuffer<IntegerEvent> ringBuffer = disruptor.getRingBuffer();

        while (true) {
            int num = (int) (Math.random() * 5000);
            System.out.println(Thread.currentThread().getName() + "----put " + num);
            ringBuffer.publishEvent((event, equence, data) -> event.setIntNum(data), num);
            Thread.sleep(1000);
        }
    }

}
