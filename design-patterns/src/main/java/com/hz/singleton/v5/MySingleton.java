package com.hz.singleton.v5;

/**
 * 枚举单例
 * 在引用MySingleton类时，会优先执行构造函数中的代码创建枚举对象
 * 见Main类，如果将main函数写在这个枚举类中，可以看到create.....总是优先被打印（就像是放在static代码块中一样）
 *
 * @author: zehua
 * @date: 2020/11/12 17:15
 */
public enum MySingleton {
    INSTANCE;

    MySingleton() {
        System.out.println("create....");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println("print.....");
    }


}
