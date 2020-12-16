package com.hz.io_model.testNetty.myNetty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zehua
 * @date 2020/12/12 7:31
 */
public class Main {

    int size = 3;
    Queue[] queue = new LinkedList[size];

    public Main(int size) {
        this.size = size;
    }

   /* public static void main(String[] args) {
        Main m = new Main(6);
        System.out.println(m.queue.length);
    }*/

    public static void main(String[] args) throws IOException {
        MyEventLoopGroup group = new MyEventLoopGroup(1);
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("192.168.41.1", 9090));
        serverSocket.configureBlocking(false);
        group.register(serverSocket);
    }

}
