package com.hz.container.map;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * WeakHashMap的简单实现，会有点小问题，因为执行垃圾回收时，有时不会立即将对象放置到引用队列中
 * WeakHashMap也会有这个问题
 *
 * @author zehua
 * @date 2020/11/28 6:14
 */
public class MyWeakHashMap {
    private static ReferenceQueue<Person> referenceQueue = new ReferenceQueue<>();
    private static Entry[] table = new Entry[2];

    private static int len = 0;

    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put("add", "aaa");
        weakHashMap.put("agf", "aaa");
        weakHashMap.put("aaab", "aaa");
        weakHashMap.put("sdfe", "aaa");
        weakHashMap.put("ac", "aaa");
        Person person = new Person();

        weakHashMap.put(new Person(), "aaabb");
        System.out.println(weakHashMap);
        System.gc();
        System.out.println(weakHashMap.size());

        /*MyWeakHashMap myWeakHashMap = new MyWeakHashMap();
        Person p1 = new Person(1);
        Person p2 = new Person(2);
        MyWeakHashMap.put(p1, "aaaa");
        MyWeakHashMap.put(p2, "bbbb");
        System.out.println(myWeakHashMap);
        p1 = null;
        System.out.println(myWeakHashMap);
        System.gc();
        //System.out.println(referenceQueue.poll());
        System.out.println(myWeakHashMap);*/
    }

    private static class Entry<K> extends WeakReference<K> {
        private int index;
        private String value;

        public Entry(K key, String value, ReferenceQueue<? super K> q, int index) {
            super(key, q);
            this.value = value;
            this.index = index;
        }

        @Override
        public String toString() {
            return "entry: index=" + index + "; value=" + value;
        }
    }

    public static void put(Person key, String vlaue) {
        final Entry<Person> entry = new Entry<>(key, vlaue, referenceQueue, key.getI());
        table[key.getI() / 2] = entry;
        len++;
    }

    public void removeInner() {
        Object o = null;

        while ((o = referenceQueue.poll()) != null) {
            synchronized (referenceQueue) {
                Entry<Person> entry = (Entry<Person>) o;
                int index = entry.index / 2;
                table[index] = null;
                len--;
            }
        }
    }

    @Override
    public String toString() {
        removeInner();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("MyWeakHashMap: ");
        for (int i = 0; i < 2; i++) {
            stringBuilder.append(table[i] + "-----");
        }
        stringBuilder.append('\n');

        return stringBuilder.toString();
    }
}
