package com.zehua.review.io;

import java.io.*;

public class DataInputOutputStreamTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String destFileName = "bbb.txt";
        String originFileFullName = path + File.separator + originFileName;
        String destFileFullName = path + File.separator + destFileName;

        File file = new File(originFileFullName);
        File destFile = new File(destFileFullName);

        testDataInputStream(file);
    }

    private static void initFile(File file) {
        try {
            if (file.exists()) {
                return;
            }

            file.createNewFile();
            testDataOutputStream(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testDataInputStream(File file) {
        initFile(file);

        try (
                InputStream is = new FileInputStream(file);
                DataInputStream dis = new DataInputStream(is);
        ) {
            String str = dis.readUTF();
            boolean b = dis.readBoolean();
            int anInt = dis.readInt();

            System.out.println(str + "\n" + b + "\n" + anInt);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testDataOutputStream(File file) {
        try (
                OutputStream os = new FileOutputStream(file);
                DataOutputStream dos = new DataOutputStream(os);
        ) {
            dos.writeUTF("泽华start");
            dos.writeBoolean(false);
            dos.writeInt(10086);

            dos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
