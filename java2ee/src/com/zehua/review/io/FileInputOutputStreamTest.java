package com.zehua.review.io;

import com.zehua.review.ReviewUtils;

import java.io.*;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:53
 **/
public class FileInputOutputStreamTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String destFileName = "bbb.txt";
        String originFileFullName = path + File.separator + originFileName;
        String destFileFullName = path + File.separator + destFileName;

        File file = new File(originFileFullName);
        File destFile = new File(destFileFullName);

        // 测试FileInputStream
        // testFileInputStream(file);

        // 测试文件复制
        testFileInOutputStream(file, destFile);

    }


    public static void testFileInputStream(File file) {
        ReviewUtils.initFileWithChinese(file);
        try (
                FileInputStream fis = new FileInputStream(file)
        ) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = fis.read(buffer)) != -1) {
                System.out.println(new String(buffer, 0, length));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testFileOutputStream(File file, String data) {
        ReviewUtils.testFileOutputStream(file, data);
    }

    public static void testFileInOutputStream(File origin, File dest) {
        ReviewUtils.initFileNoChinese(origin);
        ReviewUtils.initFileNoChinese(dest);
        try (
                FileInputStream fis = new FileInputStream(origin);
                FileOutputStream fos = new FileOutputStream(dest);
        ) {
            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
