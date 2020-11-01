package com.zehua.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @Auther zehua
 * @Date 2020/11/1 16:02
 */
public class TestReflect {

    public static void main(String[] args) throws Exception {
        Class clazz = Class.forName("com.zehua.entity.Student");
        /*
        getFields
        获取当前类以及从父类继承的属性(只有public)
         */
        System.out.println("================getFields======================");
        Field[] fields = clazz.getFields();
        for (Field f : fields
        ) {
            System.out.println("name: " + f.getName() + " type: " + f.getType());
        }
        /*
        getDeclaredFields
        获取当前类的所有属性（包括私有）
         */
        System.out.println("==================getDeclaredFields===========================");
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field f : declaredFields
        ) {
            System.out.println("name: " + f.getName() + " type: " + f.getType());
        }
        // 修改私有属性
        Field address = clazz.getDeclaredField("address");
        address.setAccessible(true);
        Object instance = clazz.getConstructor().newInstance();
        address.set(instance, "洪福桥");
        System.out.println(instance);
        /*
        同getFields
         */
        System.out.println("=======================getMethods========================");
        Method[] methods = clazz.getMethods();
        for (Method m : methods
        ) {
            System.out.println("name: " + m.getName() + " 访问修饰符类型：" + m.getModifiers());
        }
        /*
        同getDeclaredFields
         */
        System.out.println("==========================getDeclaredMethods==================");
        Method[] declaredMethods = clazz.getDeclaredMethods();
        for (Method m : declaredMethods
        ) {
            System.out.println("name: " + m.getName() + " 访问修饰符类型：" + m.getModifiers());
        }
        //调用私有方法
        Method add = clazz.getDeclaredMethod("add", int.class, int.class);
        add.setAccessible(true);
        Object invoke = add.invoke(instance, 1, 2);
        System.out.println(invoke);

        /*
        getConstructors
        获取当前类的所有public构造方法
         */
        System.out.println("============================getConstructors==================");
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor con : constructors
        ) {
            System.out.println("name: " + con.getName() + "访问修饰符类型：" + con.getModifiers());
        }

         /*
        getDeclaredConstructors
        获取当前类的所有构造方法(包括私有)
         */
        System.out.println("=============================getDeclaredConstructors=============");
        Constructor[] declaredConstructors = clazz.getDeclaredConstructors();
        for (Constructor con : declaredConstructors
        ) {
            System.out.println("name: " + con.getName() + "访问修饰符类型：" + con.getModifiers());
        }
        // 通过私有构造方法创建对象
        Constructor declaredConstructor = clazz.getDeclaredConstructor(String.class, int.class, String.class);
        declaredConstructor.setAccessible(true);
        Object zehua = declaredConstructor.newInstance("zehua", 22, "201500301038");
        System.out.println(zehua);
    }


}
