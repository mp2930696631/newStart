package com.test.classLoader;

/**
 * @author zehua
 * @date 2021/2/21 11:56
 */
public class HelloWorld {
    public void print(){
        System.out.println("hello world....in "+HelloWorld.class.getClassLoader());
    }
}
