package com.hz.observer.v2.source;

import com.hz.observer.v2.SourceObj;
import com.hz.observer.v2.event.ClickEvent;
import com.hz.observer.v2.event.Event;
import com.hz.observer.v2.listener.Listener;

import java.util.ArrayList;

/**
 * 事件源对象
 *
 * @Auther zehua
 * @Date 2020/11/8 7:31
 */
public class Button implements SourceObj {
    ArrayList<Listener> al = new ArrayList<>();

    public void addClickListener(Listener listener) {
        this.al.add(listener);
    }

    public void click() {
        Event event = new ClickEvent(this);
        for (int i = 0; i < al.size(); i++) {
            Listener listener = al.get(i);
            listener.doWork(event);
        }
    }

    @Override
    public String getSourceName() {
        return "button";
    }
}
