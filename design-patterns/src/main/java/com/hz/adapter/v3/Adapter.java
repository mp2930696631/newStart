package com.hz.adapter.v3;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:17
 */
public class Adapter implements USB {
    private Other other;

    public Adapter() {

    }

    public Adapter(Other other) {
        this.other = other;
    }

    public void hander() {
        other.doWork();
    }
}
