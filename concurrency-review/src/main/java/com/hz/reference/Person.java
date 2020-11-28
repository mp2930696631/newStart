package com.hz.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author zehua
 * @date 2020/11/28 6:37
 */
public class Person extends WeakReference {
    private String name;

    Person() {
        super("xxx");
    }

    Person(String name) {
        super("xxx");
        this.name = name;
    }


    public Person(Object referent, ReferenceQueue q, String name) {
        super(referent, q);
        this.name = name;
    }

    @Override
    public String toString() {
        return "person " + name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("delete");
    }
}
