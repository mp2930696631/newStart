package com.hz.lru;

/**
 * @author zehua
 * @date 2020/12/20 8:52
 */
public class Main {

    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.print();
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.print();
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.print();
        lRUCache.get(1);    // 返回 1
        lRUCache.print();
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.print();
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.print();
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.print();
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.print();
        lRUCache.get(3);    // 返回 3
        lRUCache.print();
        lRUCache.get(4);    // 返回 4
        lRUCache.print();

    }

}
