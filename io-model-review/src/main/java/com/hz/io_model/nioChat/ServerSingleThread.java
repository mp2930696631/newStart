package com.hz.io_model.nioChat;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author zehua
 * @date 2020/12/11 11:16
 */
public class ServerSingleThread {
    Selector selector = null;
    ServerSocketChannel serverSocket = null;
    int port = 9090;

    ServerSingleThread() {
        try {
            this.selector = Selector.open();
            this.serverSocket = ServerSocketChannel.open();
            this.serverSocket.bind(new InetSocketAddress(port));
            serverSocket.configureBlocking(false);
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSingleThread server = new ServerSingleThread();
        server.start();
    }

    public void start() throws IOException {
        System.out.println("server start......");
        while (true) {
            if (selector.select() > 0) {
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    final SelectionKey next = iterator.next();
                    iterator.remove();
                    if (next.isAcceptable()) {
                        acceptHandler(next);
                    } else if (next.isReadable()) {
                        readHandler(next);
                    } else {
                        System.out.println("wait....");
                    }
                }
            }
        }
    }

    private void readHandler(SelectionKey next) throws IOException {
        SocketChannel socket = (SocketChannel) next.channel();
        ByteBuffer byteBuffer = (ByteBuffer) next.attachment();
        byteBuffer.clear();
        final int read = socket.read(byteBuffer);
        if (read > 0) {
            Utils.readThenWriteData(socket, byteBuffer);
        } else if (read == 0) {
            System.out.println("null....");
        } else {
            System.out.println("client disconnected...");
            next.cancel();
            socket.close();
        }
    }

    private void acceptHandler(SelectionKey next) throws IOException {
        ServerSocketChannel serverSocket = (ServerSocketChannel) next.channel();
        final SocketChannel socket = serverSocket.accept();
        if (socket != null) {
            socket.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            socket.register(selector, SelectionKey.OP_READ, byteBuffer);
            byteBuffer.put(("port: " + socket.socket().getPort() + " connected.....").getBytes());
            byteBuffer.flip();
            socket.write(byteBuffer);
        }
    }

}
