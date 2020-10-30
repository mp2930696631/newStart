package com.zehua.review.net.Upload;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Auther zehua
 * @Date 2020/10/30 12:14
 */
public class Server {

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        String pictureName = "uploadPicture.jpg";
        String fullName = path + File.separator + pictureName;

        File file = new File(fullName);
        try (
                ServerSocket serverSocket = new ServerSocket(10086);
                Socket socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                FileOutputStream fos = new FileOutputStream(file);
        ) {
            int length = 0;
            byte[] buffer = new byte[512];
            while ((length = is.read(buffer)) != -1) {
                fos.write(buffer, 0, length);
            }

            os.write("上传成功！".getBytes());
            os.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
