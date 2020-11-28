package com.hz.container.collection.list;

import java.util.Stack;

/**
 * @author zehua
 * @date 2020/11/28 8:38
 */
public class MyStack {

    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push("a");
        stack.push("b");
        System.out.println(stack.peek());
        System.out.println(stack);
    }

}
