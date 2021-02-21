package com.hz.testSuper;

/**
 * @author zehua
 * @date 2021/2/18 18:31
 */
public class Child extends Parent {
    @Override
    public void m1() {
        super.m1();
    }

    @Override
    public void m2() {
        System.out.println("child----m2...");
    }
}
