package com.hz.io_model;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author zehua
 * @date 2020/12/11 4:47
 */
public class Client {

    public static void main(String[] args) throws Exception{
        String eth0 = "192.168.0.102";
        String eth1 = "192.168.41.1";
        int serverPort = 9090;
        String serverAddr = "192.168.41.100";

        SocketChannel client1 = null;
        SocketChannel client2 = null;

        ArrayList<SocketChannel> arrayList = new ArrayList<>();

        SocketAddress serverSocketAddr = new InetSocketAddress(serverAddr, serverPort);

        for (int i = 10000; i <= 65535; i++) {
            // Thread.sleep(1000);
            try {
                client1 = SocketChannel.open();
                client2 = SocketChannel.open();

                client2.bind(new InetSocketAddress(eth1, i));
                client2.connect(serverSocketAddr);
                arrayList.add(client1);

                client1.bind(new InetSocketAddress(eth0, i));
                client1.connect(serverSocketAddr);
                arrayList.add(client2);
            } catch (Exception e) {

            }
        }

        System.out.println("clients count: " + arrayList.size());
    }

}
