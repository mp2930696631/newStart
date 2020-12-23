package com.hz.projectPractice.multiThreadSelector;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

/**
 * accept 事件单用一个线程
 * 很完美的一个版本
 *
 * @author zehua
 * @date 2020/12/23 7:39
 */
public class MySelectors {
    private int selectorNum = 0;
    private SingleSelector serverSelector;
    private SingleSelector[] selectors;
    private ServerSocketChannel serverSocket;
    // 因为这个东西只会被一个线程使用，所以不用加volatile
    private int i = 0;

    public MySelectors() {
        this.selectorNum = 3;
        this.selectors = new SingleSelector[this.selectorNum];
    }

    public MySelectors(int selectorNum) {
        this.selectorNum = selectorNum;
        this.selectors = new SingleSelector[this.selectorNum];
    }

    private void init() {
        this.serverSelector = new SingleSelector();
        for (int i = 0; i < this.selectorNum; i++) {
            selectors[i] = new SingleSelector();
        }
    }

    public void registerServer(ServerSocketChannel serverSocket) {
        this.serverSocket = serverSocket;
        init();
    }

    public void run() {
        System.out.println("server start...");
        if (this.serverSocket == null) {
            System.out.println("please invock registerServer method first...");
            return;
        }

        serverSelector.registerServerSocket(this.serverSocket);
        new Thread(serverSelector,"server thread ").start();
        for (int i = 0; i < this.selectorNum; i++) {
            new Thread(selectors[i]).start();
        }
    }


    private class SingleSelector implements Runnable {
        private Selector selector;
        // 这里不使用volatile是不可见的，但是在这里的实验中，结果显示是线程可见的，原因可能是因为wakeup方法！！
        // 当我注掉了wakeup后，并且改成这样发现也是可以的selector.select(10)，
        // 原因是因为selector.select方法是synchronized！！
        // 所以，得出结论，synchronized可以是缓存失效
        // 虽然如此，但是涉及到多线程，最好还是加上volatile
        private volatile Queue<Channel> channelQueue;

        public Selector getSelector() {
            return selector;
        }

        public void setSelector(Selector selector) {
            this.selector = selector;
        }

        public Queue<Channel> getChannelQueue() {
            return channelQueue;
        }

        public void setChannelQueue(Queue<Channel> channelQueue) {
            this.channelQueue = channelQueue;
        }

        public SingleSelector() {
            try {
                // 这句代码对于epoll来说就相当于开辟一块mmap空间
                // 这句代码对于poll、select来说相当于在运行时数据区的堆中开辟一块放fd的空间
                this.selector = Selector.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
            channelQueue = new ArrayDeque<>();
        }

        private void handlerAccept(SelectionKey next) {
            final ServerSocketChannel serverSocket = (ServerSocketChannel) next.channel();
            SocketChannel socket = null;
            try {
                socket = serverSocket.accept();
                socket.configureBlocking(false);
                System.out.println(Thread.currentThread().getName() + " accept...one client");
            } catch (IOException e) {
                e.printStackTrace();
            }

            // 分配到其他selector中
            int index = (i++) % selectorNum;
            final SingleSelector singleSelector = selectors[index];
            singleSelector.getChannelQueue().add(socket);
            singleSelector.getSelector().wakeup();
        }

        public void registerServerSocket(ServerSocketChannel serverSocket) {
            try {
                // 这句代码对于epoll来说相当于epoll_ctl往mmap的红黑树上注册fd
                // 这句代码对于poll、select来说就是往堆中存放fd
                serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            } catch (ClosedChannelException e) {
                e.printStackTrace();
            }
        }

        private void handlerRead(SelectionKey next) {
            final ByteBuffer byteBuffer = (ByteBuffer) next.attachment();
            final SocketChannel socket = (SocketChannel) next.channel();
            byteBuffer.clear();
            int read = 0;
            try {
                read = socket.read(byteBuffer);
                if (read > 0) {
                    final int position = byteBuffer.position();
                    byte[] clientData = new byte[position];
                    // 注意，这里需要flip一下!!!因为position不等于0
                    byteBuffer.flip();
                    byteBuffer.get(clientData);
                    System.out.println(Thread.currentThread().getName() + "  handle read...");
                    System.out.println("client> " + new String(clientData));

                    byteBuffer.clear();
                    byteBuffer.put("server> ".getBytes());
                    byteBuffer.put(clientData);
                    // 这里也需要flip一下，因为已经put过了，position的位置也不为0
                    byteBuffer.flip();
                    socket.write(byteBuffer);
                } else if (read == 0) {

                } else {
                    System.out.println("one client disconnected.....");
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    // 这句代码对于epoll来说就是epoll_wait从链表中取fd
                    // 这句代码对于poll、select来说就是将堆中的fd传入内核并接受放回结果
                    if (selector.select() > 0) {
                        final Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        final Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            final SelectionKey next = iterator.next();
                            iterator.remove();
                            if (next.isAcceptable()) {
                                handlerAccept(next);
                            } else if (next.isReadable()) {
                                handlerRead(next);
                            }
                        }
                    }

                    while (!channelQueue.isEmpty()) {
                        final SocketChannel socket = (SocketChannel) channelQueue.poll();
                        socket.register(selector, SelectionKey.OP_READ, ByteBuffer.allocateDirect(1024));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
