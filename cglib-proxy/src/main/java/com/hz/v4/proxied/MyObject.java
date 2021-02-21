package com.hz.v4.proxied;

/**
 * @author zehua
 * @date 2021/2/18 11:28
 */
public class MyObject {

    public String getObjectName() {
        System.out.println("invoke getObjectName=----");
        // int i = 1 / 0;
        return "myObject,...";
    }

}
