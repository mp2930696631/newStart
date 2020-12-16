package com.hz.io_model.nioChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author zehua
 * @date 2020/12/11 11:16
 */
public class Client {

    public static void main(String[] args) throws IOException {
        SocketChannel socket = SocketChannel.open();
        socket.configureBlocking(false);
        socket.connect(new InetSocketAddress(9090));
        while (socket.finishConnect()) {
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            final int read = socket.read(byteBuffer);
            if (read > 0) {
                Utils.readThenWriteData(socket, byteBuffer);
            } else if (read == 0) {
                continue;
            } else {
                System.out.println("server disconnected....");
                socket.close();
            }
        }
    }

}
