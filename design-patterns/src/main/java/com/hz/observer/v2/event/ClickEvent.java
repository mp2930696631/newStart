package com.hz.observer.v2.event;

import com.hz.observer.v2.SourceObj;
import com.hz.observer.v2.event.Event;

/**
 * 具体事件，包含一个事件源对象
 *
 * @Auther zehua
 * @Date 2020/11/8 7:36
 */
public class ClickEvent implements Event {
    private SourceObj source;

    public ClickEvent() {
    }

    public ClickEvent(SourceObj source) {
        this.source = source;
    }

    @Override
    public SourceObj getSource() {
        return source;
    }
}
