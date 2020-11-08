package com.hz.observer.v2.event;

import com.hz.observer.v2.SourceObj;

/**
 * @Auther zehua
 * @Date 2020/11/8 9:14
 */
public class WindowMinEvent implements Event {
    private SourceObj source;

    public WindowMinEvent() {
    }

    public WindowMinEvent(SourceObj source) {
        this.source = source;
    }
    @Override
    public SourceObj getSource() {
        return source;
    }
}
