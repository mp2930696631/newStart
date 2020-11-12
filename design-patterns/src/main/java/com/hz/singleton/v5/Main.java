package com.hz.singleton.v5;

/**
 * @author: zehua
 * @date: 2020/11/12 18:42
 */
public class Main {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println("......");
                MySingleton instance = MySingleton.INSTANCE;
                instance.print();
                System.out.println(Thread.currentThread().getName() + " " + instance);
            }, "thread" + i).start();
        }
    }
}
