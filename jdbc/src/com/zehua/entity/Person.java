package com.zehua.entity;

/**
 * @Auther zehua
 * @Date 2020/11/1 16:06
 */
public class Person {
    public String name;
    public int age;

    public Person(){

    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
