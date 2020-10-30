package com.zehua.review.io;

import com.zehua.review.ReviewUtils;

import java.io.*;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:10
 **/
public class InputOutputStreamWriteReader {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String destFileName = "bbb.txt";
        String originFileFullName = path + File.separator + originFileName;
        String destFileFullName = path + File.separator + destFileName;

        File file = new File(originFileFullName);
        File destFile = new File(destFileFullName);

        testOutputStreamWriter(file, "泽华 abc\n123456\ntest io流\ntestOutputStreamWriter");
        testInputStreamReader(file);

    }

    public static void testInputStreamReader(File file) {
        ReviewUtils.initFileWithChinese(file);

        try (
                InputStream is = new FileInputStream(file);
                InputStreamReader isr = new InputStreamReader(is);
        ) {
            char[] buffer = new char[1024];
            int length = 0;
            while ((length = isr.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testOutputStreamWriter(File file, String data) {
        try (
                FileOutputStream fos = new FileOutputStream(file);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
        ) {
            osw.write(data);
            osw.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
