package com.hz.lru;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * leetcode 146题
 * 详情可以查看README.md
 *
 * 但是，请注意，本代码双向链表使用的是LinkedLisk，所有put的时间复杂度并不是O(1)
 *
 * @author zehua
 * @date 2020/12/20 8:29
 */
public class LRUCacheWithLinkedList {
    private int capacity;
    private HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
    private LinkedList<Integer> linkedList = new LinkedList<Integer>();
    private int size = 0;

    public LRUCacheWithLinkedList(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        final Integer value = hashMap.get(key);
        if (value != null) {
            put(key, value);
        }

        return value == null ? -1 : value;
    }

    public void put(int key, int value) {
        remove(key);

        hashMap.put(key, value);
        linkedList.addLast(key);
        size++;
    }

    private void remove(int key) {
        final Integer value = hashMap.get(key);
        // 如果key存在。则直接删除
        if (value != null) {
            hashMap.remove(key);
            linkedList.remove(Integer.valueOf(key));
            size--;
        } else {
            // 如果key不存在，且容器已满，移除第一个
            if (size >= capacity) {
                final Integer first = linkedList.removeFirst();
                hashMap.remove(first);
                size--;
            }
            // 否则，什么都不做
        }
    }

    public void print() {
        System.out.println(linkedList);
    }

}
