package com.hz.io_model.testNIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author zehua
 * @date 2020/12/11 5:22
 */
public class NIOServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("192.168.41.100", 9090));
        serverSocketChannel.configureBlocking(false);
        ArrayList<SocketChannel> al = new ArrayList<>();
        while (true) {
            final SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
                // InetSocketAddress inetSocketAddress = ((InetSocketAddress) (socketChannel.getRemoteAddress()));
                // inetSocketAddress.getHostName()这段代码底层多了很多的系统调用，慢的要死
                // System.out.println("client connected,addr: " + inetSocketAddress.getHostName() + " port: " + inetSocketAddress.getPort());
                System.out.println("client connected, port: " + socketChannel.socket().getPort());
                al.add(socketChannel);
            }

            for (SocketChannel channel : al) {
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                final int read = channel.read(byteBuffer);
                if (read > 0) {
                    byteBuffer.flip();
                    // 注意 byteBuffer.array()此函数值只针对堆内buffer，所有在此不能调用
                    // System.out.println(new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit()));
                    byteBuffer.flip();
                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    System.out.println(new String(bytes, 0, bytes.length));
                } else if (read < 0) {
                    channel.close();
                    al.remove(channel);
                }
            }
        }
    }

}
