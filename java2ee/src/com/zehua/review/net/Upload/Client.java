package com.zehua.review.net.Upload;

import java.io.*;
import java.net.Socket;

/**
 * 注意，客户端在传输大文件的时候，需要调用socket.shutdownOutput();标识客户端传输完成
 * 注意，客户端在传输大文件的时候，需要调用socket.shutdownOutput();标识客户端传输完成
 * @Auther zehua
 * @Date 2020/10/30 12:14
 */
public class Client {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String pictureName = "picture.jpg";
        String fullName = path + File.separator + pictureName;

        File file = new File(fullName);
        if (!file.exists()) {
            System.out.println("picture does not exist, return...");
            return;
        }

        try (
                Socket socket = new Socket("localhost", 10086);
                FileInputStream fis = new FileInputStream(file);
                OutputStream os = socket.getOutputStream();
                InputStream is = socket.getInputStream();
        ) {
            int length = 0;
            byte[] buffer = new byte[1024];
            while ((length = fis.read(buffer)) != -1) {
                os.write(buffer, 0, length);
                os.flush();
            }
            // 注意在传输大文件的时候，需要加上这代码来标识传输完成
            socket.shutdownOutput();

            int read = is.read(buffer);
            System.out.println(new String(buffer, 0, read));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
