package com.hz.v5.proxied;

/**
 * @author zehua
 * @date 2021/2/18 11:28
 */
public class MyObject {

    public String getObjectName() {
        System.out.println("invoke getObjectName=----");
        m();
        // int i = 1 / 0;
        return "myObject,...";
    }

    public void m() {
        System.out.println("m.....");
    }

}
