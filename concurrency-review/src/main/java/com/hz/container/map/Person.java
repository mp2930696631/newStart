package com.hz.container.map;

/**
 * @author zehua
 * @date 2020/11/28 6:26
 */
public class Person {
    private int i;

    public Person() {
    }

    public Person(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "person";
    }
}
