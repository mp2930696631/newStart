package com.hz.spring_ioc_xml.factory;

import com.hz.spring_ioc_xml.entity.Address;
import com.hz.spring_ioc_xml.entity.Person;

/**
 * @Auther zehua
 * @Date 2020/11/6 7:38
 */
public class MyFactory {

    public static Person getPersonStatic(String name) {
        return new Person(name, 22, new Address());
    }

    public Person getPerson(String name) {
        return new Person(name, 22, new Address());
    }
}
