package com.zehua.review.io;

import com.zehua.review.ReviewUtils;

import java.io.*;

public class BufferedReaderWriterTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String destFileName = "bbb.txt";
        String originFileFullName = path + File.separator + originFileName;
        String destFileFullName = path + File.separator + destFileName;

        File file = new File(originFileFullName);
        File destFile = new File(destFileFullName);

        // testBufferedReader(file);

        testBufferedWriter(file, "小泽\n重asdfgrewg123\n题");
    }

    public static void testBufferedReader(File file) {
        ReviewUtils.initFileWithChinese(file);
        try (
                Reader reader = new FileReader(file);
                BufferedReader br = new BufferedReader(reader);
        ) {

            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testBufferedWriter(File file, String data) {
        try (
                Writer writer = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(writer);
        ) {

            bw.write(data);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
