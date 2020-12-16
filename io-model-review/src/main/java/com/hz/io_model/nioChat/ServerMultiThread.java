package com.hz.io_model.nioChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;

/**
 * 使用线程池处理accept、read事件
 *
 * @author zehua
 * @date 2020/12/11 12:40
 */
public class ServerMultiThread {

    ThreadPoolExecutor executorService = null;
    ServerSocketChannel serverSocket = null;
    Selector selector = null;
    int port = 9090;
    String serverAddr = "192.168.41.1";

    ServerMultiThread() {
        try {
            this.serverSocket = ServerSocketChannel.open();
            this.selector = Selector.open();
            this.executorService = new ThreadPoolExecutor(8, 8, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
            this.serverSocket.bind(new InetSocketAddress(serverAddr, port));
            InetSocketAddress inetSocketAddress = (InetSocketAddress) this.serverSocket.getLocalAddress();
            System.out.println("server addr: " + inetSocketAddress.getHostName());
            this.serverSocket.configureBlocking(false);
            this.serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerMultiThread server = new ServerMultiThread();
        server.start();
    }

    public void start() throws IOException {
        System.out.println("server start...........");
        while (true) {
            //System.out.println(".......");
            // System.out.println(selector.select() + "-----------------");
            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            // 如果使用selector.select()因为会永久阻塞，如果是在其他线程注册了fd的话，当前线程可能不可见
            // 所以这种模型不行！！！得使用selector.select(xxx)
            int num = selector.select(10);
            //System.out.println("--------------");
            if (num > 0) {
                final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    final SelectionKey next = iterator.next();
                    iterator.remove();
                    if (next.isAcceptable()) {
                        //next.cancel();
                        this.executorService.execute(() -> {
                            acceptHandler(next);
                        });
                    } else if (next.isReadable()) {
                        // 为了防止这个next key被重复调起，需要将其从红黑树删除，然后再重新注册！！！
                        next.cancel();
                        this.executorService.execute(() -> {
                            readHandler(next);
                        });
                    }
                }
            }
        }
    }

    private void readHandler(SelectionKey next) {
        // System.out.println("read handler.............");
        SocketChannel socket = (SocketChannel) next.channel();
        ByteBuffer byteBuffer = (ByteBuffer) next.attachment();

        byteBuffer.clear();
        try {
            // 这一步内部是同步方法。所以在多线程的环境下，当有很多线程执行了readHandler的时候，只有一个线程可以读取数据，其他的会独到的是0
            // 当客户端关闭的时候，这个方法也会出问题，因为一个线程会将该socket关闭，其他线程在来read就会报错
            final int read = socket.read(byteBuffer);
            if (read > 0) {
                // 重新注册
                socket.register(selector, SelectionKey.OP_READ, byteBuffer);
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                System.out.println("port: " + socket.socket().getPort() + new String(bytes, 0, bytes.length));
                byteBuffer.clear();
                byteBuffer.put(bytes);
                byteBuffer.flip();
                socket.write(byteBuffer);
            } else if (read == 0) {
                System.out.println("null....");
            } else if (read < 0) {
                System.out.println("client closed........");
                socket.close();
                next.cancel();
            }

        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("has closed...");
        }

    }

    private void acceptHandler(SelectionKey next) {
        // System.out.println("accept....");
        ServerSocketChannel serverSocket = (ServerSocketChannel) next.channel();
        try {
            // 多线程并发竞争serverSocket资源，存在问题
            final SocketChannel socket = serverSocket.accept();
            if (socket == null) {
                return;
            }
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            socket.configureBlocking(false);
            socket.register(this.selector, SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
            final int remotePort = socket.socket().getPort();
            System.out.println("client port: " + remotePort);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            byteBuffer.put(("port: " + remotePort + " connected.....").getBytes());
            byteBuffer.flip();
            socket.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
