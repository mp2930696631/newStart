package com.hz.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author zehua
 * @date 2020/11/28 7:00
 */
public class MyPhantomReference {

    public static void main(String[] args) {
        Person person = new Person();
        ReferenceQueue<Person> personReferenceQueue = new ReferenceQueue<>();
        PhantomReference<Person> w = new PhantomReference<>(person, personReferenceQueue);
        System.out.println(personReferenceQueue.poll());
        System.gc();
        System.out.println(personReferenceQueue.poll());
        person = null;
        System.out.println(personReferenceQueue.poll());
        System.gc();
        System.out.println(personReferenceQueue.poll());

    }

}
