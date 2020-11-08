package com.hz.observer.v1;

/**
 * 请先阅读README.md文件
 * 最简易的观察者模式
 * 当点击button的时候就弹窗
 * 就是一个简单的方法调用
 *
 * @Auther zehua
 * @Date 2020/11/8 7:32
 */
public class Main {

    public static void main(String[] args) {
        Button button = new Button();
        button.click();
    }

}
