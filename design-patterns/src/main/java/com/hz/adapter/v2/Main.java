package com.hz.adapter.v2;

/**
 * 对象适配器，使用组合的方式，而不是使用继承
 *
 * @Auther zehua
 * @Date 2020/11/8 5:28
 */
public class Main {

    public static void main(String[] args) {
        Computer computer = new Computer();
        NetLine netLine = new NetLine();
        USBAdapter adapter = new NetAdapter(netLine);
        computer.net(adapter);
    }

}
