package com.zehua.review.net.UserLogin;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 * 1、密码可以输错五次。五次后自动断开
 * 2、scanner对象也最好定义成全局的
 * @Auther zehua
 * @Date 2020/10/30 12:05
 */
public class Client {
    private static final String SUCCEED_MSG = "login succeed";
    private static final String MAX_LOGIN_TIME_MSG = "too many login time, disconnect...";

    // 当客户端再次启动时，会重新创建System.in对象和scanner对象
    // 但是最好也是定义一个全局唯一的scanner
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        OutputStream os = null;
        ObjectOutputStream oos = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        Scanner scanner = new Scanner(System.in);
        Socket socket = null;
        try {
            socket = new Socket("localhost", 10086);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            String result = "";

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);

            is = socket.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            while (!result.equals(SUCCEED_MSG) && !result.equals(MAX_LOGIN_TIME_MSG)) {
                System.out.println("请输入用户名：");
                String username = scanner.nextLine();
                System.out.println("请输入密码：");
                String passwd = scanner.nextLine();

                User user = new User(username, passwd);

                oos.writeObject(user);
                oos.flush();

                result = br.readLine();
                System.out.println("server>>: " + result);
            }

            if (result.equals(SUCCEED_MSG) ) {
                chatToServer(socket);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                isr.close();
                is.close();
                oos.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static boolean chatToServer(Socket socket) {
        try (
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                // 客户端的scanner放在这个地方不会有问题
                // 因为exit后，客户端会退出
                // 当客户端再次启动时，会重新创建System.in对象和scanner对象
                // 但是最好也是定义一个全局唯一的scanner
                // Scanner scanner = new Scanner(System.in);
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
        ) {
            String str = "";
            String msg = "";
            while (!msg.equals("exit")) {
                msg = scanner.nextLine();
                osw.write(msg + "\n");
                osw.flush();
                if (msg.equals("exit")) {
                    return false;
                }

                str = br.readLine();
                System.out.println("server msg>>: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
