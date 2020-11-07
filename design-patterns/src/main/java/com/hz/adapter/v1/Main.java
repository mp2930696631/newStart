package com.hz.adapter.v1;

/**
 * 类适配器，使用继承的方式
 *
 * @Auther zehua
 * @Date 2020/11/8 5:21
 */
public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();
        USBAdapter adapter = new NetAdapter();
        computer.net(adapter);
    }

}
