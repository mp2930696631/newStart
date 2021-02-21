package com.test;

import com.hz.clazzLoader.ClazzLoader;
import com.test.classLoader.HelloWorld;

import java.lang.reflect.Constructor;

/**
 * @author zehua
 * @date 2021/2/21 12:01
 *
 * 测试AppClassLoader加载class的顺序与-classpath相关，
 * -classpath下可以存在完全限定名一样的class，谁排在前面就加载谁，排在后面的不会被加载了
 *
 * 因为我将com.test.classLoader.HelloWorld这个类所在的路径放入了classpath，并且排在本项目的classpath的前面，
 * 所有，加载的HelloWorld并不是本项目的HelloWorld
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ClazzLoader clazzLoader = new ClazzLoader();
        final Class<?> aClass = clazzLoader.loadClass("com.test.classLoader.HelloWorld");
        final Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
        final HelloWorld helloWorld = (HelloWorld) declaredConstructor.newInstance();
        helloWorld.print();
        // declaredConstructor.newInstance();
    }

}
