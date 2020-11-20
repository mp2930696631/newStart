package com.zehua.testThreadLocal.other;

/**
 * 注意，这一点与DateUtil中的使用方式有点不同，不过也可以改成那种方式
 * 只是这里使用一种新的方式
 * 需要先调用set方法
 * 这样。每次获取的i的值都是固定的，线程安全
 *
 * @author zehua
 * @date 2020/11/20 22:29
 */
public class OtherWith {
    public ThreadLocal<MyIntObj> myIntObjThreadLocal = new ThreadLocal<>();

    public void set() {
        myIntObjThreadLocal.set(new MyIntObj());
    }

    public void add() {
        myIntObjThreadLocal.get().i += 1;
    }

    public void minus() {
        myIntObjThreadLocal.get().i -= 5;
    }

    public void multi() {
        myIntObjThreadLocal.get().i *= 5;
    }

    public static void main(String[] args) {
        final OtherWith otherWith = new OtherWith();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                otherWith.set();
                otherWith.add();
                otherWith.minus();
                otherWith.multi();
                System.out.println(otherWith.myIntObjThreadLocal.get().i);
            }).start();
        }
    }
}
