package com.hz.myTomcat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 注意：可能浏览器会向后端发送一些其他请求，所以在获取className的时候需要做一下非空判断
 * 不然会出现意想不到的以外
 *
 * @Auther zehua
 * @Date 2020/11/5 20:03
 */
public class Server {

    public void service() {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(10086);
            while (true) {
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();

                MyRequest request = new MyRequest(is);
                MyResponse response = new MyResponse(os);
                String uri = request.getUri();
                String className = new Mapping().getClassName(uri);

                if (className != null) {
                    Class clazz = Class.forName(className);
                    MyServletAbs abs = (MyServletAbs) clazz.getConstructor().newInstance();
                    // MyServletAbs abs = new MyServlet();
                    abs.service(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Server().service();
    }

}
