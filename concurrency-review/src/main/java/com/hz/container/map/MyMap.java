package com.hz.container.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/11/27 19:26
 */
public class MyMap {
    private static int a = 2;
    private static int count = 1000;

    static UUID[] keys = new UUID[a * count];
    static UUID[] values = new UUID[a * count];

    static CountDownLatch cdl1 = new CountDownLatch(a);
    static CountDownLatch cdl2 = new CountDownLatch(a);
    static CountDownLatch cdl3 = new CountDownLatch(a);
    static CountDownLatch cdl4 = new CountDownLatch(a);

    static {
        for (int i = 0; i < a * count; i++) {
            keys[i] = UUID.randomUUID();
            values[i] = UUID.randomUUID();
        }
    }


    public MyMap() {

    }

    public static void testPut() throws InterruptedException {
        Hashtable hashtable = new Hashtable();
        long start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            new MyThread(i, hashtable, cdl1).start();
        }
        cdl1.await();
        long end = System.currentTimeMillis();
        System.out.println("hashtab put---" + (end - start) + "---size=" + hashtable.size());

        HashMap hashMap = new HashMap();
        start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            new MyThread(i, hashMap, cdl2).start();
        }
        cdl2.await();
        end = System.currentTimeMillis();
        System.out.println("hashmap put---" + (end - start) + "----size=" + hashMap.size());

        HashMap hashMap2 = new HashMap();
        final Map synchronizedMap = Collections.synchronizedMap(hashMap2);
        start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            new MyThread(i, synchronizedMap, cdl3).start();
        }
        cdl3.await();
        end = System.currentTimeMillis();
        System.out.println("synchronizedMap put---" + (end - start) + "----size=" + synchronizedMap.size());

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            new MyThread(i, concurrentHashMap, cdl4).start();
        }
        cdl4.await();
        end = System.currentTimeMillis();
        System.out.println("concurrentHashMap put---" + (end - start) + "----size=" + concurrentHashMap.size());
    }

    public static void testGet() throws Exception {
       /* Hashtable hashtable = new Hashtable();
        for (int i = 0; i < a * count; i++) {
            hashtable.put(keys[i], values[i]);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            new MyThread(i, hashtable, cdl1).start();
        }
        cdl1.await();
        long end = System.currentTimeMillis();
        System.out.println("hashtab put---" + (end - start) + "---size=" + hashtable.size());

        HashMap hashMap = new HashMap();
        for (int i = 0; i < a * count; i++) {
            hashMap.put(keys[i], values[i]);
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            new MyThread(i, hashMap, cdl2).start();
        }
        cdl2.await();
        end = System.currentTimeMillis();
        System.out.println("hashmap put---" + (end - start) + "----size=" + hashMap.size());

        HashMap hashMap2 = new HashMap();
        final Map synchronizedMap = Collections.synchronizedMap(hashMap2);
        for (int i = 0; i < a * count; i++) {
            synchronizedMap.put(keys[i], values[i]);
        }
        start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            new MyThread(i, synchronizedMap, cdl3).start();
        }
        cdl3.await();
        end = System.currentTimeMillis();
        System.out.println("synchronizedMap put---" + (end - start) + "----size=" + synchronizedMap.size());
*/
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (int i = 0; i < a * count; i++) {
            concurrentHashMap.put(keys[i], values[i]);
        }
        //start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            new MyThread(i, concurrentHashMap, cdl4).start();
        }
        cdl4.await();
        //end = System.currentTimeMillis();
        //System.out.println("concurrentHashMap put---" + (end - start) + "----size=" + concurrentHashMap.size());
    }


    public static void main(String[] args) throws Exception {
        // MyMap.testPut();
        MyMap.testGet();
    }

    public static class MyThread extends Thread {
        int index = 0;
        Map map;
        CountDownLatch cdl;

        public MyThread(int index, Map map, CountDownLatch cdl) {
            this.index = index;
            this.map = map;
            this.cdl = cdl;
        }

        @Override
        public void run() {
            for (int i = 0; i < count; i++) {
                // map.put(keys[i + index * count], values[i + index * count]);
                map.get(keys[i + index * count]);
            }
            cdl.countDown();
        }
    }

}
