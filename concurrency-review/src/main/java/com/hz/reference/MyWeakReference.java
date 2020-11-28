package com.hz.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author zehua
 * @date 2020/11/28 6:36
 */
public class MyWeakReference {

    public static void main(String[] args) {
        Person person = new Person("zehua");
        ReferenceQueue<Person> personReferenceQueue = new ReferenceQueue<>();
        // WeakReference<Person> w = new WeakReference<>(person, personReferenceQueue);
        Person w = new Person(person, personReferenceQueue, "xiaoze");
        System.out.println(w.get());
        System.gc();
        System.out.println(w.get());
        person = null;
        System.gc();
        System.out.println(w.get());
        System.out.println(personReferenceQueue.poll());
    }

}
