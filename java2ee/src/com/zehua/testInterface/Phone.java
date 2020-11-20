package com.zehua.testInterface;

/**
 * @author zehua
 * @date 2020/11/20 22:01
 */
public abstract class Phone {
    private String brand;
    private String type;

    public Phone(String brand, String type) {
        this.brand = brand;
        this.type = type;
    }

    public abstract void sendMsg();
    public abstract void call();
}
