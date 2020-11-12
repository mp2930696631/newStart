package com.hz.singleton.v1;

/**
 * 饿汉式单例
 *
 * @author: zehua
 * @date: 2020/11/12 17:15
 */
public class MySingleton {
    private static MySingleton mySingleton = new MySingleton();

    private MySingleton() {

    }

    public void print() {
        System.out.println("get.....ok");
    }

    public static MySingleton getInstance() {
        return mySingleton;
    }

    public static void main(String[] args) {
        MySingleton instance = MySingleton.getInstance();
        instance.print();
    }

}
