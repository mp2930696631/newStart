package com.hz.observer.v2.source;

import com.hz.observer.v2.SourceObj;
import com.hz.observer.v2.event.Event;
import com.hz.observer.v2.event.WindowCloseEvent;
import com.hz.observer.v2.event.WindowMaxEvent;
import com.hz.observer.v2.event.WindowMinEvent;
import com.hz.observer.v2.listener.Listener;

import java.util.ArrayList;

/**
 * @Auther zehua
 * @Date 2020/11/8 9:14
 */
public class Window implements SourceObj {
    ArrayList<Listener> alMax = new ArrayList<>();
    ArrayList<Listener> alMin = new ArrayList<>();
    ArrayList<Listener> alClose = new ArrayList<>();

    public void addMaxListener(Listener listener) {
        this.alMax.add(listener);
    }

    public void addMinListener(Listener listener) {
        this.alMin.add(listener);
    }

    public void addCloseListener(Listener listener) {
        this.alClose.add(listener);
    }

    public void max() {
        Event event = new WindowMaxEvent(this);
        for (int i = 0; i < alMax.size(); i++) {
            Listener listener = alMax.get(i);
            listener.doWork(event);
        }

    }

    public void min() {
        Event event = new WindowMinEvent(this);
        for (int i = 0; i < alMin.size(); i++) {
            Listener listener = alMin.get(i);
            listener.doWork(event);
        }

    }

    public void close() {
        Event event = new WindowCloseEvent(this);
        for (int i = 0; i < alClose.size(); i++) {
            Listener listener = alClose.get(i);
            listener.doWork(event);
        }

    }

    @Override
    public String getSourceName() {
        return "window";
    }
}
