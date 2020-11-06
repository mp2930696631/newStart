package com.hz.spring_ioc_anno.entity;

/**
 * @Auther zehua
 * @Date 2020/11/6 12:42
 */
public class Animal {

    private String type;

    public Animal() {
    }

    public Animal(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "type='" + type + '\'' +
                '}';
    }
}
