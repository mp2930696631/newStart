package com.zehua.review.net.UserLogin;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 注意：采用一问一答模式，客户端登录成功后，需先发送消息，然后服务器一句，客户端一句
 * server端不断接收服务器端的连接，挺另起一个线程去处理与服务器端的通信
 * 需要注意的有两点
 * 1、每次发送数据的时候，末尾需要带上\n用于标识信息发送端已经发送完数据
 * 1、每次发送数据的时候，末尾需要带上\n用于标识信息发送端已经发送完数据
 * 1、每次发送数据的时候，末尾需要带上\n用于标识信息发送端已经发送完数据
 * 2、scanner对象最好定义为一个全局变量，避免出现java.util.NoSuchElementException: No line found
 * @Auther zehua
 * @Date 2020/10/30 9:31
 */
public class Server {

    private static final String USER_NAME = "zehua";
    private static final String PASSWD = "123456";

    // 因为当scanner关闭之后，System.in也会关闭
    // 而Syetem.in对象是一个static final 对象，所以再新建scanner也会出问题
    // 最好是定义一个全局唯一的scanner对象
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(10086);
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                Thread thread = new Thread(new DoTask(socket));
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static class DoTask implements Runnable {
        private Socket socket;

        public DoTask() {

        }

        public DoTask(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            int count = 1;
                InputStream is = null;
                ObjectInputStream ois = null;
                OutputStream os = null;
                try {
                    is = socket.getInputStream();
                    ois = new ObjectInputStream(is);
                    os = socket.getOutputStream();

                    while (true){
                        Object o = ois.readObject();
                        User user = null;
                        if (o instanceof User) {
                            user = (User) o;
                        } else {
                            System.out.println("out......not server you");
                            break;
                        }

                        String username = user.getUsername();
                        String passwd = user.getPasswd();
                        if (username.equals(USER_NAME) && passwd.equals(PASSWD)) {
                            os.write("login succeed\n".getBytes());
                            os.flush();

                            boolean flag = chatToClient(socket);
                            if (!flag) {
                                break;
                            }
                        } else {
                            if (count > 4) {
                                os.write("too many login time, disconnect...\n".getBytes());
                                os.flush();
                                break;
                            } else {
                                os.write("username or password is wrong, please login again\n".getBytes());
                                os.flush();
                                count++;
                            }
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        os.close();
                        ois.close();
                        is.close();
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        }
    }

    /**
     * 一个死循环，直到客户端输入exit才结束
     * @param socket 服务器端socket对象
     * @return false表示结束
     */
    public static boolean chatToClient(Socket socket) {

        try (
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                // 注意Scanner放在这个位置会出问题，因为当scanner关闭之后，System.in也会关闭
                // 而Syetem.in对象是一个static final 对象，所以再新建scanner也会出问题
                // 最好是定义一个全局唯一的scanner对象
                // Scanner scanner = new Scanner(System.in);
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
        ) {
            String str = "";
            String msg = "";
            while (!str.equals("exit")) {
                str = br.readLine();
                System.out.println("client msg>>: " + str);
                if (str.equals("exit")) {
                    return false;
                }
                msg = scanner.nextLine();
                osw.write(msg + "\n");
                osw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
