package com.hz.observer.v3.event;

import com.hz.observer.v3.SourceObj;

/**
 * @Auther zehua
 * @Date 2020/11/8 9:13
 */
public class WindowCloseEvent implements Event {
    private SourceObj source;

    public WindowCloseEvent() {
    }

    public WindowCloseEvent(SourceObj source) {
        this.source = source;
    }

    @Override
    public SourceObj getSource() {
        return source;
    }
}
