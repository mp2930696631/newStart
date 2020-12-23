package com.hz.projectPractice.multiThreadSelector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;

/**
 * @author zehua
 * @date 2020/12/23 7:36
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("192.168.41.1", 9090));
        serverSocket.configureBlocking(false);
        MySelectors mySelectors = new MySelectors();
        mySelectors.registerServer(serverSocket);
        mySelectors.run();
    }

}
