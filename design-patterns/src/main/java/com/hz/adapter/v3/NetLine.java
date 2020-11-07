package com.hz.adapter.v3;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:17
 */
public class NetLine implements Other {

    public void request() {
        System.out.println("可以上网了......");
    }

    @Override
    public void doWork() {
        this.request();
    }
}
