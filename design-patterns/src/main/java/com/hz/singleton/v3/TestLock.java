package com.hz.singleton.v3;

/**
 * 测试对象锁和类锁
 *
 * @author: zehua
 * @date: 2020/11/12 17:40
 */
public class TestLock {

    public synchronized void print() {
        System.out.println(Thread.currentThread().getName() + " print....");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void other() {
        System.out.println(Thread.currentThread().getName() + " other....");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void staticPrint() {
        System.out.println(Thread.currentThread().getName() + " static print.....");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void staticPrintOther() {
        System.out.println(Thread.currentThread().getName() + " static print other.....");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 测试对象锁，对象锁之间互不影响
        /*for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                TestLock testLock = new TestLock();
                testLock.print();
            }).start();
        }*/
        // 测试同一对象锁互刺
        /*TestLock testLock = new TestLock();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                testLock.print();
            }).start();
        }*/

        // 测试对象锁和类锁不互斥, 类锁和类锁互斥
        /*new Thread(() -> {
            TestLock.staticPrint();
        }).start();

        new Thread(() -> {
            TestLock.staticPrintOther();
        }).start();*/
    }
}

