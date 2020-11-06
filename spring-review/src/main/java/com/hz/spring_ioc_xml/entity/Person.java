package com.hz.spring_ioc_xml.entity;

/**
 * @Auther zehua
 * @Date 2020/11/6 5:26
 */
public class Person {
    private String name;
    private int age;
    public Address address;

    public void init() {
        System.out.println("person init....");
    }

    public void destroy() {
        System.out.println("person destroy....");
    }

    public Person() {
        System.out.println("person created....");
    }

    public Person(Address address) {
        this.address = address;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address=" + address +
                '}';
    }
}
