package com.hz.threadPool;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author zehua
 * @date 2020/11/28 19:00
 */
public class TestThreadLocalRandom {

    public static void main(String[] args) {
        final ThreadLocalRandom current = ThreadLocalRandom.current();
        final int i = current.nextInt(10);
        System.out.println(i);
    }

}
