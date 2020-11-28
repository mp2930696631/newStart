package com.hz.container.collection.queue;

import java.util.ArrayDeque;

/**
 * @author zehua
 * @date 2020/11/28 9:22
 */
public class MyArrayDeque {

    public static void main(String[] args) {
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add("aaa");
        arrayDeque.add("bbbb");
        arrayDeque.add("vvvv");
        System.out.println(arrayDeque);
    }

}
