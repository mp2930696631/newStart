package com.zehua.review.io;

import com.zehua.review.ReviewUtils;

import java.io.*;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:10
 */
public class BufferedInputOutStreamTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String destFileName = "bbb.txt";
        String originFileFullName = path + File.separator + originFileName;
        String destFileFullName = path + File.separator + destFileName;

        File file = new File(originFileFullName);
        File destFile = new File(destFileFullName);

        // testBufferedInputStream(file);

        testBufferedOutputStream(file, "123456\nzhuehuafhdui\nfe456789");
    }

    public static void testBufferedInputStream(File file) {
        ReviewUtils.initFileNoChinese(file);

        try (
                InputStream is = new FileInputStream(file);
                BufferedInputStream bis = new BufferedInputStream(is)
        ) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = bis.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testBufferedOutputStream(File file, String data) {
        try (
                OutputStream os = new FileOutputStream(file);
                BufferedOutputStream bos = new BufferedOutputStream(os);
        ) {

            bos.write(data.getBytes());
            bos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
