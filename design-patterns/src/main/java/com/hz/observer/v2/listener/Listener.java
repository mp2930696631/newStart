package com.hz.observer.v2.listener;

import com.hz.observer.v2.event.Event;

/**
 * @Auther zehua
 * @Date 2020/11/8 9:11
 */
public interface Listener {
    void doWork(Event event);
}
