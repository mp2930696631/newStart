package com.hz.io_model.testNetty.myNetty;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author zehua
 * @date 2020/12/12 7:26
 */

interface HandlerI {
    void registerRead(Channel channel, ByteBuffer byteBuffer);
}

public class MyEventLoopGroup {
    private int threadSize = 3;
    private int index = 0;
    private SelectorThread[] selectorThreads = null;

    private HandlerI handlerI;

    private Queue<Channel>[] queues;

    public MyEventLoopGroup() {
        queues = new LinkedList[threadSize];
        this.selectorThreads = new SelectorThread[threadSize];
        init();
    }

    public void addHandler(HandlerI handlerI) {
        this.handlerI = handlerI;
    }

    public void init() {
        for (int i = 0; i < threadSize; i++) {
            selectorThreads[i] = new SelectorThread();
        }

        for (int i = 0; i < threadSize; i++) {
            queues[i] = new LinkedList<>();
        }
    }

    public MyEventLoopGroup(int threadSize) {
        this.threadSize = threadSize;
        this.queues = new LinkedList[threadSize];
        this.selectorThreads = new SelectorThread[this.threadSize];
        init();
    }

    public void register(Channel channel) {
        selectorThreads[(index++) % threadSize].register(channel);

        for (int i = 0; i < threadSize; i++) {
            new Thread(selectorThreads[i]).start();
        }
    }

    private void acceptHandler(SelectionKey key) {
        ServerSocketChannel serverSocket = (ServerSocketChannel) key.channel();
        try {
            final SocketChannel socket = serverSocket.accept();
            socket.configureBlocking(false);

            int i = (index++) % threadSize;
            queues[i].add(socket);
            selectorThreads[i].setQueueId(i);
            selectorThreads[i].getSelector().wakeup();
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

    private void readHandler(SelectionKey key) {
        SocketChannel socket = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();

        byteBuffer.clear();
        try {
            final int read = socket.read(byteBuffer);
            if (read > 0) {
                byteBuffer.flip();
                byte[] bytes = new byte[byteBuffer.remaining()];
                byteBuffer.get(bytes);
                System.out.println(Thread.currentThread().getName() + "--------" + new String(bytes, 0, bytes.length));
                byteBuffer.clear();
                byteBuffer.put(bytes);
                byteBuffer.flip();
                socket.write(byteBuffer);
            } else if (read == 0) {
                System.out.println("null....");
            } else if (read < 0) {
                System.out.println("client closed........");
                socket.close();
                key.cancel();
            }

        } catch (IOException e) {
            System.out.println("has closed...");
            try {
                socket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            key.cancel();
        }

    }

    public class SelectorThread implements Runnable {
        private Selector selector;
        private int queueId;

        public Selector getSelector() {
            return selector;
        }

        // 提供set方法， 以便让该线程往任意队列去取
        public void setQueueId(int queueId) {
            this.queueId = queueId;
        }

        public void register(Channel channel) {
            try {
                if (channel instanceof ServerSocketChannel) {
                    System.out.println(Thread.currentThread().getName() + " server register........");
                    final ServerSocketChannel serverSocket = (ServerSocketChannel) channel;
                    serverSocket.register(this.selector, SelectionKey.OP_ACCEPT);
                } else if (channel instanceof SocketChannel) {
                    System.out.println(Thread.currentThread().getName() + "socket register.......");
                    SocketChannel socket = (SocketChannel) channel;
                    socket.register(this.selector, SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public SelectorThread() {
            try {
                this.selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
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
                                if (handlerI == null) {
                                    readHandler(next);
                                } else {
                                    SocketChannel channel = (SocketChannel) next.channel();
                                    final ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                                    final int read = channel.read(byteBuffer);
                                    if (read > 0) {
                                        handlerI.registerRead(channel, byteBuffer);
                                    } else if (read == 0) {
                                        System.out.println("null------");
                                    } else {
                                        System.out.println("close....");
                                        channel.close();
                                        next.cancel();
                                    }
                                }
                            } else if (next.isWritable()) {

                            }
                        }
                    }

                    if (!queues[queueId].isEmpty()) {
                        final Channel channel = queues[queueId].poll();
                        register(channel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
