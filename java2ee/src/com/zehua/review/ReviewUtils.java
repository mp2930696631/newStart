package com.zehua.review;

import com.zehua.review.lambda.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:10
 **/
public class ReviewUtils {

    public static void initFileNoChinese(File file) {
        try {
            if (file.exists()) {
                return;
            }

            file.createNewFile();
            testFileOutputStream(file, "zehua abc\n123456\ntest");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initFileWithChinese(File file) {
        try {
            if (file.exists()) {
                return;
            }

            file.createNewFile();
            testFileOutputStream(file, "泽华 abc\n123456\ntest io流");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFileOutputStream(File file, String data) {
        try (
                FileOutputStream fos = new FileOutputStream(file)
        ) {
            fos.write(data.getBytes());
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFileWriter(File file, String data) {
        try (
                FileWriter fw = new FileWriter(file)
        ) {
            fw.write(data);
            fw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> generateStudents() {
        ArrayList<Student> students = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            IntStream intStream = random.ints(60, 101);
            int grade = intStream.findFirst().getAsInt();
            students.add(new Student("zehua", grade));
        }

        return students;
    }

}
