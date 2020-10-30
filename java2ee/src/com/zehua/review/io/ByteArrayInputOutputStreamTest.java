package com.zehua.review.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:31
 **/
public class ByteArrayInputOutputStreamTest {

    public static void main(String[] args) {
        testByteArrayInputStream();
        testByteArrayOutputStream();
    }

    public static void testByteArrayOutputStream() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            baos.write("泽华\nagergr\n天天".getBytes());
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void testByteArrayInputStream() {
        byte[] buffer = "123456\nfahughadg\ngandfhueqtnvaoidhgiiu".getBytes();
        ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
        try {
            int length = bais.read(buffer);
            System.out.println(new String(buffer, 0, length));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
