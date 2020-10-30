package com.zehua.review;

import java.io.*;

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

}
