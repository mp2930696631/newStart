package com.hz.spring_aop_anno.proxy;

import org.springframework.stereotype.Component;

/**
 * @Auther zehua
 * @Date 2020/11/6 13:47
 */
@Component
public class MyCalculator {

    public int add(int a, int b) {
        return a + b;
    }


    public int minus(int a, int b) {
        return a - b;
    }


    public int divide(int a, int b) {
        int result = 0;

            result = a / b;

        return result;
    }


    public int multi(int a, int b) {
        return a * b;
    }
}
