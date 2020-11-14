package com.hz.practice.entity;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author: zehua
 * @date: 2020/11/14 9:13
 */
public class User {
    @DateTimeFormat
    private String name;
    private int age;

    public User(){}

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                       "name='" + name + '\'' +
                       ", age=" + age +
                       '}';
    }
}
