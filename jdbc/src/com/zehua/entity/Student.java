package com.zehua.entity;

/**
 * @Auther zehua
 * @Date 2020/11/1 16:07
 */
public class Student extends Person {
    public String sid;
    private String address;

    private Student(String name, int age, String sid) {
        super(name, age);
        this.sid = sid;
    }

    Student(String name, int age) {
        super(name, age);
    }

    public Student() {
    }

    public Student(String name, int age, String sid, String address) {
        super(name, age);
        this.sid = sid;
        this.address = address;
    }


    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private int add(int a, int b) {
        return a + b;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
