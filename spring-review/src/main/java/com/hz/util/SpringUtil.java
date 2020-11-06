package com.hz.util;

import com.hz.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * util类
 * 主要是为了编写模拟spring扫描类的过程的代码而存在
 * 有以下几个点需要注意
 * 1、当使用迭代器进行遍历list的时候，不能对元素进行删除，否则会报java.util.ConcurrentModificationException
 * 2、由于ArrayList的特点，当删除一个元素的时候，下标需要对应的减一，因为涉及元素的移位操作
 *
 * @Auther zehua
 * @Date 2020/11/6 10:01
 */
public class SpringUtil {

    // 获取某文件夹以及子文件夹下所有文件
    public static void getFilesAbsName(File file, ArrayList<String> result) throws IOException {
        if (file.isFile()) {
            result.add(file.getCanonicalPath());
        } else {
            File[] files = file.listFiles();
            if (files != null && files.length != 0) {
                for (File file1 : files) {
                    getFilesAbsName(file1, result);
                }
            }
        }
    }

    /**
     * 通过classpath+dotDirname
     * @param dotDirname 以.为分割符号的classpath下的路径
     * @return 文件夹File对象
     */
    public static File getFileByDotSeparator(String dotDirname) {
        String classpath = getClasspath();
        String dirname = dotDirname.replace(".", "/");
        String fileName = classpath + dirname;

        return new File(fileName);
    }

    /**
     * 根据文件的全路径获取class文件的完全限定名
     * @param path class文件的绝对路径
     * @return
     */
    public static String getClassFullName(String path) {
        String classpath = getClasspath();
        String temp = path.substring(classpath.length(), path.length() - ".class".length());
        String classFullName = temp.replace("\\", ".");

        return classFullName;
    }

    // 移除不需要注入容器的bean
    public static void removeNotNeedScan(ArrayList<String> al) {
        for (int i = 0; i < al.size(); i++) {
            String classFullname = al.get(i);
            Class<?> aClass = null;
            try {
                aClass = Class.forName(classFullname);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
            Annotation[] annotationsClass = aClass.getAnnotations();
            boolean flag = false;
            for (Annotation annotation : annotationsClass) {
                if (annotation.annotationType() == Component.class) {
                    flag = true;
                    break;
                } else if (annotation.annotationType() == Controller.class) {
                    flag = true;
                    break;
                } else if (annotation.annotationType() == Service.class) {
                    flag = true;
                    break;
                } else if (annotation.annotationType() == Repository.class) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                // 注意，移除元素的时候需要改变下标值，因为Array是采用数值来存储元素的，删除元素的话涉及移位操作
                al.remove(classFullname);
                i--;
            }
        }

        // 使用foreach迭代器进行操作的时候，不能删除元素，否则会报java.util.ConcurrentModificationException
        /*al.forEach(classFullname -> {
            Class<?> aClass = null;
            try {
                aClass = Class.forName(classFullname);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                return;
            }
            Annotation[] annotationsClass = aClass.getAnnotations();
            boolean flag = false;
            for (Annotation annotation : annotationsClass) {
                if (annotation.annotationType() == Component.class) {
                    flag = true;
                    break;
                } else if (annotation.annotationType() == Controller.class) {
                    flag = true;
                    break;
                } else if (annotation.annotationType() == Service.class) {
                    flag = true;
                    break;
                } else if (annotation.annotationType() == Repository.class) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                al.remove(classFullname);
            }
        });*/
    }

    // 实际干活，实例化bean，并保存在map中
    public static Map<String, Object> doLoadClass(ArrayList<String> al) throws Exception {
        Map<String, Object> map = new HashMap<>();

        while (al.size() > 0) {
            for (int i = 0; i < al.size(); i++) {
                String classFullPath = al.get(i);
                boolean flag = loadClass(classFullPath, map);
                if (flag) {
                    al.remove(classFullPath);
                    i--;
                }
            }
        }

        return map;
    }

    // 获取当前的classpath路径，由于是使用单元测试，所有该classpath后面带有test-classes/ 需要进行字符串截取
    private static String getClasspath() {
        String s = Main.class.getResource("/").toString();
        String classpath = s.substring(6, s.length() - "test-classes/".length()) + "classes/";
        return classpath;
    }

    private static boolean loadClass(String classFullPath, Map<String, Object> map) throws Exception {
        Class aClass = Class.forName(classFullPath);
        String classname = classFullPath.substring(classFullPath.lastIndexOf(".") + 1);
        String beanName = classname.substring(0, 1).toLowerCase() + classname.substring(1);
        if (!canBeInstance(aClass, map)) {
            return false;
        }

        Object instance = aClass.getConstructor().newInstance();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Annotation[] annotations = declaredField.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == Autowired.class) {
                    String fieldName = declaredField.getName();
                    Object o = map.get(fieldName);
                    declaredField.setAccessible(true);
                    declaredField.set(instance, o);
                }
            }
        }

        map.put(beanName, instance);
        return true;
    }

    // 只有当该对象中Autowired属性对于的对象全部实例化之后再讲本对象实例化
    private static boolean canBeInstance(Class<?> clazz, Map<String, Object> map) {
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Annotation[] annotations = declaredField.getAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation.annotationType() == Autowired.class) {
                    String fieldName = declaredField.getName();
                    Object o = map.get(fieldName);
                    // 还存在没有实例化的依赖，返回
                    if (o == null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }


}
