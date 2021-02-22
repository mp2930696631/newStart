package com.hz.testThreadLocal;

import com.hz.entity.Person;

/**
 * @author zehua
 * @date 2021/2/22 8:31
 *
 * 需要使用debug模式
 *
 * ThreadLocal中没有使用引用队列！！
 */
public class Main {
    // 该threadLocal对象是用来测试的
    static ThreadLocal<Person> tl = new ThreadLocal<>() {
        @Override
        protected Person initialValue() {
            return new Person("zehua");
        }

        @Override
        protected void finalize() throws Throwable {
            System.out.println("destroy....");
        }
    };

    // 该threadLocal对象是用来查看gc后，对应线程相应的map中到底还有多少entry，使用debug模式
    static ThreadLocal<Person> tt = ThreadLocal.withInitial(() -> new Person());

    public static void main(String[] args) {
        new Thread(() -> {
            Person p = tl.get();
            tt.get();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 在这里打一个断点，然后跟进ThreadLocal的源码
            tt.remove();
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // tl指向的对象的强引用消失
        tl = null;
        // gc，这时，tl指向的对象被回收，可以看到控制台打印了destroy....
        System.gc();
    }

}
