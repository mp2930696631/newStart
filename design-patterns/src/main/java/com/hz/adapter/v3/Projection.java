package com.hz.adapter.v3;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:42
 */
public class Projection implements Other {
    @Override
    public void doWork() {
        this.project();
    }

    public void project() {
        System.out.println("可以投影l..............");
    }
}
