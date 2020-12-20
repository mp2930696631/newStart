package com.hz.lru;

import java.util.HashMap;

/**
 * 使用自定义的双向链表，而不是使用LinkedList
 * 这样put的时间复杂度才能达到O(1)
 *
 * @author zehua
 * @date 2020/12/20 9:12
 */
public class LRUCache {
    private int capacity;
    private HashMap<Integer, Node> hashMap = new HashMap<>();
    private MyLinkedList linkedList = new MyLinkedList();
    private int size = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        final Node node = hashMap.get(key);
        if (node != null) {
            put(key, node);
        }

        return node == null ? -1 : node.value;
    }

    public void put(int key, int value) {
        Node node = new Node(key, value);

        put(key, node);
    }

    private void put(int key, Node node) {
        remove(key);

        hashMap.put(key, node);
        linkedList.addLast(node);
        size++;
    }

    private void remove(int key) {
        final Node node = hashMap.get(key);
        // 如果key存在。则直接删除
        if (node != null) {
            hashMap.remove(key);
            linkedList.remove(node);
            size--;
        } else {
            // 如果key不存在，且容器已满，移除第一个
            if (size >= capacity) {
                final Node first = linkedList.removeFirst();
                hashMap.remove(first.key);
                size--;
            }
            // 否则，什么都不做
        }
    }

    public void print() {
        System.out.println(linkedList);
    }

    /**
     * head 和tail永远存在，但是内部没有key、value
     */
    private class MyLinkedList {
        private Node head, tail;

        public MyLinkedList() {
            this.head = new Node();
            this.tail = new Node();
            head.next = tail;
            tail.pre = head;
        }


        public void remove(Node node) {
            Node tPre = node.pre;
            Node tNext = node.next;
            tPre.next = tNext;
            tNext.pre = tPre;
        }

        public Node removeFirst() {
            Node first = head.next;
            remove(first);

            return first;
        }

        public void addFirst(Node node) {
            Node n = head.next;
            node.next = n;
            head.next = node;
            n.pre = node;
            node.pre = head;
        }

        public void addLast(Node node) {
            Node n = tail.pre;
            node.pre = n;
            tail.pre = node;
            n.next = node;
            node.next = tail;
        }

        @Override
        public String toString() {
            Node h = head;
            StringBuilder sb = new StringBuilder();
            sb.append("{");
            while ((h = h.next) != tail) {
                sb.append(h.key);
                sb.append("=");
                sb.append(h.value);
                if (h.next != tail) {
                    sb.append(", ");
                }
            }

            sb.append("}");

            return sb.toString();
        }
    }

    private class Node {
        private Integer key;
        private Integer value;
        private Node next;
        private Node pre;

        public Node() {

        }

        public Node(Integer key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public Node(Node next, Node pre) {
            this.next = next;
            this.pre = pre;
        }

        public Node(Integer key, Integer value, Node next, Node pre) {
            this.key = key;
            this.value = value;
            this.next = next;
            this.pre = pre;
        }
    }

}
