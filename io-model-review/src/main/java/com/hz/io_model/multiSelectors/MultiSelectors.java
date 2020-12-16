package com.hz.io_model.multiSelectors;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 每个selector单独跑线程，就可以避免可见性问题了
 *
 * @author zehua
 * @date 2020/12/11 17:30
 */
public class MultiSelectors {
    int index = 0;
    int size = 3;
    SelectThread[] selectThreads = new SelectThread[size];

    // 这里不一定需要使用阻塞队列，使用普通的队列即可，因为最多一个线程放，一个线程拿，没有冲突
    Queue<Channel>[] queue = new LinkedList[size];

    public void initThreadAndStart() {
        for (int i = 0; i < size; i++) {
            queue[i] = new LinkedList<>();
        }

        for (int i = 0; i < size; i++) {
            selectThreads[i] = new SelectThread(i);
        }

        for (int i = 0; i < size; i++) {
            new Thread(selectThreads[i]).start();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.configureBlocking(false);
        serverSocket.bind(new InetSocketAddress("192.168.41.1", 9090));

        MultiSelectors multiSelectors = new MultiSelectors();
        multiSelectors.initThreadAndStart();
        // multiSelectors.queue[0].put(serverSocket);
        multiSelectors.queue[0].add(serverSocket);
        // multiSelectors.selectThreads[0].register(serverSocket);
        multiSelectors.selectThreads[0].selector.wakeup();
    }


    public void acceptHandler(SelectionKey key) {
        ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
        try {
            final SocketChannel socket = serverSocket.accept();
            socket.configureBlocking(false);
            // socket.register(this.selector, SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
            //queue[index % size].put(socket);
            queue[index % size].add(socket);
            selectThreads[(index++) % size].selector.wakeup();
            //selectThreads[(index++) % size].register(socket);
            final int remotePort = socket.socket().getPort();
            System.out.println(Thread.currentThread().getName() + " client port: " + remotePort);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
            byteBuffer.put(("port: " + remotePort + " connected.....").getBytes());
            byteBuffer.flip();
            socket.write(byteBuffer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readHandler(SelectionKey next) {
        SocketChannel socket = (SocketChannel) next.channel();
        ByteBuffer byteBuffer = (ByteBuffer) next.attachment();

        byteBuffer.clear();
        try {
            final int read = socket.read(byteBuffer);
            if (read > 0) {
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                System.out.println(new String(bytes, 0, bytes.length));
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
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            next.cancel();
        }

    }

    public class SelectThread implements Runnable {
        Selector selector = null;

        int id = 0;

        public SelectThread(int id) {
            this.id = id;
            try {
                this.selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void register(Channel channel) {
            try {
                if (channel instanceof ServerSocketChannel) {
                    System.out.println("server register........");
                    final ServerSocketChannel serverSocket = (ServerSocketChannel) channel;
                    serverSocket.register(this.selector, SelectionKey.OP_ACCEPT);
                } else if (channel instanceof SocketChannel) {
                    System.out.println("socket register.......");
                    SocketChannel socket = (SocketChannel) channel;
                    socket.register(this.selector, SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
                }
            } catch (Exception e) {

            }
        }

        @Override
        public void run() {
            System.out.println("selector start......");
            while (true) {
                try {
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
                            }
                        }
                    }

                    if (!queue[id].isEmpty()) {
                        System.out.println(Thread.currentThread().getName() + " take " + queue[id].isEmpty());
                        final Channel channel = queue[id].poll();
                        //final Channel channel = queue[id].take();
                        register(channel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
