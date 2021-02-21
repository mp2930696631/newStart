package com.hz.tomcatClassLoader.classLoaders;

import com.hz.Servlet;
import com.hz.classFiles.ClassFiles;
import com.hz.clazzLoader.ClazzLoader;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * @author zehua
 * @date 2021/2/21 10:43
 *
 *
 */
public class Main {
    private static String servlet01 = "F:\\zehua\\software\\idea\\workspace\\newStart\\jvm-review\\project01\\target\\classes\\com\\hz\\servlet\\MyServlet.class";
    private static String servlet02 = "F:\\zehua\\software\\idea\\workspace\\newStart\\jvm-review\\project02\\target\\classes\\com\\hz\\servlet\\MyServlet.class";

    public static void main(String[] args) throws Exception {
        ClazzLoader clazzLoader01 = new ClazzLoader("F:\\zehua\\software\\idea\\workspace\\newStart\\jvm-review\\project01\\target\\classes\\");
        ClazzLoader clazzLoader02 = new ClazzLoader("F:\\zehua\\software\\idea\\workspace\\newStart\\jvm-review\\project02\\target\\classes\\");

        // 分别加载
        final ArrayList<File> allClassFiles01 = ClassFiles.getAllClassFiles("F:\\zehua\\software\\idea\\workspace\\newStart\\jvm-review\\project01\\target\\classes");
        final ArrayList<File> allClassFiles02 = ClassFiles.getAllClassFiles("F:\\zehua\\software\\idea\\workspace\\newStart\\jvm-review\\project02\\target\\classes");

        /*for (File file : allClassFiles01) {
            clazzLoader01.loadClass(file);
        }

        for (File file : allClassFiles02) {
            clazzLoader02.loadClass(file);
        }*/

        File servlet01File = new File(servlet01);
        File servlet02File = new File(servlet02);

        // 只需要加载入口的class就可以了，就像tomcat中，servlet就是入口，也就是只要每个servlet一个classloader就没问题
        final Class<?> servlet01Class = clazzLoader01.loadClass(servlet01File);
        final Class<?> servlet02Class = clazzLoader02.loadClass(servlet02File);
        final Constructor<?> servlet01ClassDeclaredConstructor = servlet01Class.getDeclaredConstructor();
        final Constructor<?> servlet02ClassDeclaredConstructor = servlet02Class.getDeclaredConstructor();
        Servlet servlet01In = (Servlet) servlet01ClassDeclaredConstructor.newInstance();
        Servlet servlet02In = (Servlet) servlet02ClassDeclaredConstructor.newInstance();
        servlet01In.service();
        servlet02In.service();
        /*servlet01ClassDeclaredConstructor.newInstance();
        servlet02ClassDeclaredConstructor.newInstance();*/
        /*System.out.println(Servlet.class.getClassLoader());
        System.out.println(servlet01In instanceof Servlet);
        System.out.println(servlet01In.getClass().isAssignableFrom(Servlet.class));
        System.out.println(Servlet.class.isAssignableFrom(servlet01In.getClass()));*/
    }

}
