package com.zehua.review.io.entity;

import java.io.Serializable;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:10
 **/
public class Student implements Serializable {
    private static final long serialVersionUID = -3604722901058134696L;

    private String name;
    private int age;
    private String school;
    private transient String idCardPsw;

    public Student() {
    }

    public Student(String name, int age, String school, String idCardPsw) {
        this.name = name;
        this.age = age;
        this.school = school;
        this.idCardPsw = idCardPsw;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getIdCardPsw() {
        return idCardPsw;
    }

    public void setIdCardPsw(String idCardPsw) {
        this.idCardPsw = idCardPsw;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school='" + school + '\'' +
                ", idCardPsw='" + idCardPsw + '\'' +
                '}';
    }
}
