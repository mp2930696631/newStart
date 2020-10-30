package com.zehua.review.thread;

/**
 * @Auther zehua
 * @Date 2020/10/30 14:42
 */
public class ByTicket {
    private static int ticket = 5;
    private static String[] chars = {"A", "B", "C", "D", "E"};

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                synchronized (ByTicket.class) {
                    while (ticket > 0) {
                        System.out.println(Thread.currentThread().getName() + " 购买了第" + (ticket--) + "票");
                        break;
                    }
                }
            }, chars[i]).start();
        }
    }

}
