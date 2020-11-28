package com.hz.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @author zehua
 * @date 2020/11/27 17:24
 */
public class IntegerEventFactory implements EventFactory<IntegerEvent> {
    @Override
    public IntegerEvent newInstance() {
        return new IntegerEvent();
    }
}
