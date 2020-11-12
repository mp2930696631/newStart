package com.hz.singleton.v3;

/**
 * 懒汉式单例(双重检查版)
 * 需要加上volatile
 *
 * @author: zehua
 * @date: 2020/11/12 17:15
 */
public class MySingleton {
    private static volatile MySingleton mySingleton;

    private MySingleton() {
        System.out.println(" create...");
        /*try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void print() {
        System.out.println("get.....ok");
    }

    public static MySingleton getInstance() {
        if (mySingleton == null) {
            synchronized (MySingleton.class) {
                if (mySingleton == null) {
                    /*
                    非原子操作
                    1、开辟一块内存空间
                    2、创建MySingleton对象
                    3、mySingleton（变量）指向这个内存空间

                    如果发生指令重排，变成132就可能出现问题
                    验证不出来。。。。。执行太快了。。。。
                     */
                    mySingleton = new MySingleton();
                }
            }

        }
        return mySingleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                MySingleton instance = MySingleton.getInstance();
                instance.print();
                System.out.println(Thread.currentThread().getName() + " " + instance);
            }, "thread" + i).start();
        }
    }

}
