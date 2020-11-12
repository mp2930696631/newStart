package com.hz.singleton.v2;

/**
 * 懒汉式单例(线程不安全版)
 *
 * @author: zehua
 * @date: 2020/11/12 17:15
 */
public class MySingleton {
    private static MySingleton mySingleton;

    private MySingleton() {
        // 为了可以看到多线程不安全的效果，加上sleep
        System.out.println(" create...");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("get.....ok");
    }

    public static MySingleton getInstance() {
        if (mySingleton == null) {
            mySingleton = new MySingleton();
        }
        return mySingleton;
    }

    public static void main(String[] args) {
        /*MySingleton instance = MySingleton.getInstance();
        instance.print();*/

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                MySingleton instance1 = MySingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + " " + instance1);
            }, "thread" + i).start();
        }
    }

}
