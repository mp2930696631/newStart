package com.hz.container.map;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author zehua
 * @date 2020/11/28 5:46
 */
public class MyConcurrentSkipListedMap {

    public static void main(String[] args) {
        ConcurrentSkipListMap concurrentSkipListMap = new ConcurrentSkipListMap();
        concurrentSkipListMap.put("a", "aaa");
        concurrentSkipListMap.put("g", "aaa");
        concurrentSkipListMap.put("b", "aaa");
        concurrentSkipListMap.put("e", "aaa");
        concurrentSkipListMap.put("c", "aaa");

        System.out.println(concurrentSkipListMap);
    }

}
