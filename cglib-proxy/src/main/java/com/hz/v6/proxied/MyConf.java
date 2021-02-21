package com.hz.v6.proxied;

/**
 * @author zehua
 * @date 2021/2/18 18:20
 */
public class MyConf {
    private MyObject myObject;

    public MyObject getMyObject() {
        return myObject;
    }

    public void setMyObject(MyObject myObject) {
        this.myObject = myObject;
    }

    public void m1() {
        System.out.println("m1....");
        m2();
    }

    public void m2() {
        if (myObject != null) {
            System.out.println(myObject.getObjectName());
        } else {
            System.out.println("m2.....and myObject is null");
        }
    }

}
