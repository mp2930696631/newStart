package com.zehua.review.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @Auther zehua
 * @Date 2020/10/30 9:32
 */
public class RandomAccessFileTest {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String originFileName = "aaa.txt";
        String originFileFullName = path + File.separator + originFileName;
        File file = new File(originFileFullName);

        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(file, "r");
            long fileLength = file.length();
            int blockSize = 1024;
            int actualSize = 0;
            int size = (int) Math.ceil(fileLength * 1.0 / blockSize);
            for (int i = 0; i < size; i++) {
                if (i == size - 1) {
                    actualSize = (int) fileLength;
                } else {
                    actualSize = blockSize;
                    fileLength -= blockSize;
                }
                System.out.println("第" + i + "块：" + "从第" + i * blockSize + "字节开始," + "实际读取长度：" + actualSize);
                randomRead(i * blockSize, actualSize, randomAccessFile);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                randomAccessFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void randomRead(long pos, int actualSize, RandomAccessFile raf) throws IOException {
        raf.seek(pos);
        byte[] buffer = new byte[512];
        int length = 0;
        StringBuffer stringBuffer = new StringBuffer();

        while ((length = raf.read(buffer)) != -1) {
            if (length >= actualSize) {
                stringBuffer.append(new String(buffer, 0, actualSize));
                break;
            } else {
                stringBuffer.append(new String(buffer, 0, length));
                actualSize -= length;
            }
        }
        System.out.println(stringBuffer.toString());
    }

}
