package com.hz.testStatic;

/**
 * @author zehua
 * @date 2021/2/18 13:00
 */
public class MyClass {
    private static String name;

    static {
        System.out.println("my class load....");
    }

    public static void setName(String name) {
        MyClass.name = name;
    }

    public static String getName() {
        return name;
    }

}
