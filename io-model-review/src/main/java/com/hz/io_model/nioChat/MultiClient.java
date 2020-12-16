package com.hz.io_model.nioChat;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author zehua
 * @date 2020/12/11 13:16
 */
public class MultiClient {

    public static void main(String[] args) throws Exception {
        String eth0 = "192.168.0.102";
        String eth1 = "192.168.41.1";
        int serverPort = 9090;
        String serverAddr = "192.168.41.100";
        // String serverAddr = "192.168.41.1";

        SocketChannel client1 = null;
        SocketChannel client2 = null;

        ArrayList<SocketChannel> arrayList = new ArrayList<>();

        SocketAddress serverSocketAddr = new InetSocketAddress(serverAddr, serverPort);

        for (int i = 10000; i <= 65535; i++) {
            System.out.println("------------");
            // Thread.sleep(1000);
            try {
                // client1 = SocketChannel.open();
                client2 = SocketChannel.open();

                client2.bind(new InetSocketAddress(eth1, i));
                client2.connect(serverSocketAddr);
                arrayList.add(client2);
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                client2.read(byteBuffer);
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                System.out.println(new String(bytes, 0, bytes.length));

                byteBuffer.clear();
                byteBuffer.put(("port: " + client2.socket().getLocalPort() + " hello server.....").getBytes());
                byteBuffer.flip();
                client2.write(byteBuffer);

            } catch (Exception e) {

            }
        }

        System.out.println("clients count: " + arrayList.size());
    }


}
