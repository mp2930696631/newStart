package com.hz.io_model.testBIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zehua
 * @date 2020/12/11 4:56
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {


        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("192.168.41.100", 9090));

        while (true) {
            final SocketChannel socket = serverSocket.accept();

            new Thread(() -> {
                try {
                    InetSocketAddress inetSocketAddress = ((InetSocketAddress) (socket.getRemoteAddress()));
                    System.out.println("client connected,addr: port: " + inetSocketAddress.getPort());
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                    final int read = socket.read(byteBuffer);
                    if (read > 0) {
                        System.out.println("read ok");
                        byteBuffer.flip();
                        byte[] bytes = new byte[byteBuffer.remaining()];
                        byteBuffer.get(bytes);
                        System.out.println(new String(bytes, 0, bytes.length));
                    } else if (read < 0) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

}
