package com.hz.io_model.testNetty.myNetty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * @author zehua
 * @date 2020/12/12 8:10
 */
public class MyNettyServer {

    public static void main(String[] args) throws IOException {
        MyEventLoopGroup group = new MyEventLoopGroup();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("192.168.41.1", 9090));
        serverSocket.configureBlocking(false);
        group.register(serverSocket);
    }

}
