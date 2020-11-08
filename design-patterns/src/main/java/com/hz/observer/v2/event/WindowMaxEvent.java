package com.hz.observer.v2.event;

import com.hz.observer.v2.SourceObj;

/**
 * @Auther zehua
 * @Date 2020/11/8 9:13
 */
public class WindowMaxEvent implements Event {
    private SourceObj source;

    public WindowMaxEvent() {
    }

    public WindowMaxEvent(SourceObj source) {
        this.source = source;
    }
    @Override
    public SourceObj getSource() {
        return source;
    }
}
