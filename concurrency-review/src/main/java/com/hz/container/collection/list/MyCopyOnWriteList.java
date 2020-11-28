package com.hz.container.collection.list;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author zehua
 * @date 2020/11/28 8:41
 */
public class MyCopyOnWriteList {

    public static void main(String[] args) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        copyOnWriteArrayList.add("aa");
        copyOnWriteArrayList.add("bbb");
        copyOnWriteArrayList.add("adf");
        System.out.println(copyOnWriteArrayList);
    }

}
