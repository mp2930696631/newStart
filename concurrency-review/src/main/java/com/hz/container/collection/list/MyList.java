package com.hz.container.collection.list;

import java.util.Vector;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zehua
 * @date 2020/11/27 21:01
 */
public class MyList {

    public static void testVector() {
        Vector vector = new Vector();
        vector.add("fff");
        System.out.println(vector);
    }

    public static void testConcurrentLinkedQueue() {
        ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();
    }

    public static void main(String[] args) {
        MyList.testVector();
    }

}
