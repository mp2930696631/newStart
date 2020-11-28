package com.hz.container.map;

import java.util.LinkedHashMap;

/**
 * @author zehua
 * @date 2020/11/28 6:00
 */
public class MyLinkedHashMap {

    public static void main(String[] args) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("adfw", "aaa");
        linkedHashMap.put("gfgv", "aaa");
        linkedHashMap.put("bafd", "aaa");
        linkedHashMap.put("ecea", "aaa");


        Person person = new Person();

        linkedHashMap.put(person, "aaabb");
        linkedHashMap.put("ccdcdf", "aaa");
        linkedHashMap.get(person);
        person = null;

        System.out.println(linkedHashMap);
        System.gc();
        System.out.println(linkedHashMap);
    }

}
