package com.hz.testStatic;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 13:00
 *
 * 静态变量以及静态方法只需要操作class对象即可，并不需要实例对象
 */
public class Main {

    public static void main(String[] args) throws Exception {
        final Class<MyClass> myClassClass = MyClass.class;
        // 加载MyClass的class对象到内存
        myClassClass.getName();

        System.out.println(MyClass.getName());
        System.out.println("-----------------");

        final Method getName = myClassClass.getDeclaredMethod("setName", String.class);
        getName.invoke(null, "zehua");
        System.out.println(MyClass.getName());
        System.out.println("-----------------");
    }

}
