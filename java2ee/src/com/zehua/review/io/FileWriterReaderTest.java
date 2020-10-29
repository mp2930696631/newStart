package com.zehua.review.io;

import com.zehua.review.ReviewUtils;

import java.io.*;

public class FileWriterReaderTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String destFileName = "bbb.txt";
        String originFileFullName = path + File.separator + originFileName;
        String destFileFullName = path + File.separator + destFileName;

        File file = new File(originFileFullName);

        // testFileReader(file);

        // testFileWriter(file, "就让大于冲刷记忆中的沙123\n让我了如牵挂浪迹在天涯");

        testFileWriterReader(file, new File(destFileName));
    }

    public static void testFileReader(File file) {
        ReviewUtils.initFileWithChinese(file);

        try (
                FileReader rf = new FileReader(file)
        ) {
            char[] buffer = new char[1024];
            int length = 0;
            while ((length = rf.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFileWriter(File file, String data) {
        ReviewUtils.testFileWriter(file, data);
    }

    public static void testFileWriterReader(File origin, File dest) {
        ReviewUtils.initFileWithChinese(origin);
        ReviewUtils.initFileWithChinese(dest);

        try (
                FileWriter fw = new FileWriter(dest);
                FileReader fr = new FileReader(origin);
        ) {
            char[] buffer = new char[1024];
            int length = 0;
            while ((length = fr.read(buffer)) != -1) {
                fw.write(buffer, 0, length);
            }
            fw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
