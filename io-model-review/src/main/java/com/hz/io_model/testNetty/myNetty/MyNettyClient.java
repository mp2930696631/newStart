package com.hz.io_model.testNetty.myNetty;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author zehua
 * @date 2020/12/12 8:10
 */
public class MyNettyClient {

    public static void main(String[] args) throws IOException {
        MyEventLoopGroup group = new MyEventLoopGroup(1);
        SocketChannel socket = SocketChannel.open();
        socket.configureBlocking(false);
        socket.connect(new InetSocketAddress("192.168.41.1", 9090));
        group.addHandler(((channel, byteBuffer) -> {
            try {
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                System.out.println(new String(bytes, 0, bytes.length));

                BufferedInputStream inputStream = new BufferedInputStream(System.in);
                byte[] buffer = new byte[1024];
                final int size = inputStream.read(buffer);
                byteBuffer.clear();
                byteBuffer.put(buffer, 0, size);
                byteBuffer.flip();
                socket.write(byteBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }));
        if (socket.finishConnect()){
            group.register(socket);
        }

    }

}
