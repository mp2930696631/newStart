package com.zehua.testThreadLocal.other;

/**
 * @author zehua
 * @date 2020/11/20 22:24
 */
public class OtherWithout {
    public MyIntObj myIntObj = new MyIntObj();

    public void add() {
        myIntObj.i += 1;
    }

    public void minus() {
        myIntObj.i -= 5;
    }

    public void multi() {
        myIntObj.i *= 5;
    }

    public static void main(String[] args) {
        final OtherWithout otherWithout = new OtherWithout();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                otherWithout.add();
                otherWithout.minus();
                otherWithout.multi();
                System.out.println(otherWithout.myIntObj.i);
            }).start();
        }
    }

}
