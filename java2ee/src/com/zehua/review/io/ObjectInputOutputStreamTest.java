package com.zehua.review.io;

import com.zehua.review.io.entity.Student;

import java.io.*;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:10
 **/
public class ObjectInputOutputStreamTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String destFileName = "bbb.txt";
        String originFileFullName = path + File.separator + originFileName;
        String destFileFullName = path + File.separator + destFileName;

        File file = new File(originFileFullName);
        File destFile = new File(destFileFullName);

        testObjectInputStream(file);
    }

    private static void initFile(File file) {
        try {
            if (file.exists()) {
                return;
            }

            file.createNewFile();
            testObjectOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testObjectInputStream(File file) {
        initFile(file);
        try (
                InputStream is = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(is);
        ) {
            Object object = ois.readObject();
            Student student;
            if (object instanceof Student) {
                student = (Student) object;
            } else {
                System.out.println("not a student instance");
                return;
            }
            System.out.println(student);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void testObjectOutputStream(File file) {
        try (
                OutputStream os = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(os);
        ) {
            oos.writeObject(new Student("泽华", 18, "SUD", "123456"));

            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
