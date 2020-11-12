package com.hz.singleton.v4;

/**
 * 静态内部类
 *
 * @author: zehua
 * @date: 2020/11/12 17:15
 */
public class MySingleton {
    private static MySingleton mySingleton;

    private MySingleton() {
        System.out.println(" create...");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class InnerClass {
        private static final MySingleton INSTANCE = new MySingleton();
    }

    public static MySingleton getInstance() {
        return InnerClass.INSTANCE;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                MySingleton instance = MySingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + " " + instance);
            }, "thread" + i).start();
        }
    }

}
