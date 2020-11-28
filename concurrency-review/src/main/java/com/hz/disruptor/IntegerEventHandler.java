package com.hz.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * @author zehua
 * @date 2020/11/27 17:25
 */
public class IntegerEventHandler implements EventHandler<IntegerEvent> {
    @Override
    public void onEvent(IntegerEvent integerEvent, long l, boolean b) throws Exception {
        System.out.println(Thread.currentThread().getName() + "----" + integerEvent.toString() + " sequence: " + l + " endOfBatch: " + b);
    }
}
