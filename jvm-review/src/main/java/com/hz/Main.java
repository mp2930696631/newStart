package com.hz;

import com.hz.clazzLoader.ClazzLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * 实现热加载，只需要调用findClass函数就可以了！！！
 *
 * 需要注意的是，同一个classLoader实例不可以重复调用defineClass函数，每次都需要重新new classLoader才行
 * 还有就是defineClass传入的名称应是类的完全限定名
 *
 * @author zehua
 * @date 2020/12/17 20:18
 */
public class Main {

    public static void main(String[] args) {
        String className = "com.test.classLoader.HelloWorld";
        /*ClazzLoader clazzLoader = new ClazzLoader();
        try {
            final Class<?> aClass = clazzLoader.loadClass(className);
            final Constructor<?> constructor = aClass.getConstructor();
            final Object o = constructor.newInstance();
            final Method print = aClass.getDeclaredMethod("print");
            print.invoke(o);
            System.out.println(aClass.getClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        for (int i = 0; i < 2; i++) {
            ClazzLoader clazzLoader = new ClazzLoader();
            try {
                final Class<?> aClass = clazzLoader.findClass(className);
                final Constructor<?> constructor = aClass.getConstructor();
                final Object o = constructor.newInstance();
                final Method print = aClass.getDeclaredMethod("print");
                print.invoke(o);
                System.out.println(aClass.getClassLoader());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
