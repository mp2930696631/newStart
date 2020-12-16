package com.hz.io_model.testSelector;

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
 * @date 2020/12/11 6:37
 */
public class SelectorServer {
    static Selector selector;
    static ServerSocketChannel serverSocket;

    public static void main(String[] args) throws Exception {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("192.168.41.1", 9090));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            while (selector.select() > 0) {
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    final SelectionKey next = iterator.next();
                    // remove必须要加，否则第二个while进不来，具体原因不详
                    iterator.remove();
                    if (next.isAcceptable()) {
                        acceptHandler(next);
                    } else if (next.isReadable()) {
                        readHandler(next);
                    }
                }
            }
        }
    }

    private static void readHandler(SelectionKey next) throws IOException {
        ByteBuffer byteBuffer = (ByteBuffer) next.attachment();
        SocketChannel socketChannel = (SocketChannel) next.channel();
        // 因为是从attachment中获取的共用的对象byteBuffer，所以最好在使用实现先调用它的clear方法
        byteBuffer.clear();
        // 不能调用reset，需要优先调用mark，还是不要用reset了
        // byteBuffer.reset();
        socketChannel.read(byteBuffer);
        // byteBuffer这巨坑，不能这么使用
        // System.out.println(new String(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit()));
        // flip使用频率真高
        byteBuffer.flip();
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
    }

    private static void acceptHandler(SelectionKey selectionKey) throws Exception {
        ServerSocketChannel serverSocket = (ServerSocketChannel) selectionKey.channel();

        final SocketChannel socketChannel = serverSocket.accept();
        socketChannel.configureBlocking(false);

        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        socketChannel.register(selector, SelectionKey.OP_READ, byteBuffer);

        InetSocketAddress inetSocketAddress = ((InetSocketAddress) (socketChannel.getRemoteAddress()));
        // System.out.println("client connected,addr: " + inetSocketAddress.getHostName() + " port: " + inetSocketAddress.getPort());
        System.out.println("client connected port: " + inetSocketAddress.getPort());
    }

}
