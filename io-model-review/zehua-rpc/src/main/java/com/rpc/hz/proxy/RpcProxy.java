package com.rpc.hz.proxy;

import com.rpc.hz.ReadUtils;
import com.rpc.hz.callback.MyCallback;
import com.rpc.hz.connectionPool.SerCliConPool;
import com.rpc.hz.entity.AddrPort;
import com.rpc.hz.procotol.RpcBody;
import com.rpc.hz.procotol.RpcHeader;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/12 12:04
 */
public class RpcProxy<T> {
    private static final int HEADER_SIZE = 69;

    private String serverAddr;
    private int serverPort;
    private AddrPort addrPort;
    // private NioSocketChannel socket;
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    private MyCallback callback = new MyCallback();

    public void setServerInfo(String serverAddr, int serverPort) {
        this.serverAddr = serverAddr;
        this.serverPort = serverPort;
        final boolean isContainAddrPort = SerCliConPool.isContainAddrPort(serverAddr, serverPort);
        if (isContainAddrPort) {
            System.out.println("contain.....");
            this.addrPort = SerCliConPool.getAddrPort(serverAddr, serverPort);
            return;
        }
        System.out.println("first not contain....");
        this.addrPort = new AddrPort(serverAddr, serverPort);
        SerCliConPool.addAddrPort(this.addrPort);
    }

    public T getInstance(ClassLoader loader, Class<T> interfaces) {
        return (T) Proxy.newProxyInstance(loader, new Class[]{interfaces}, new MyHandler());
    }

    public NioSocketChannel getSocketFromPool(AddrPort key) {

        NioSocketChannel conn = SerCliConPool.getConn(key);
        if (conn == null) {
            conn = createSocket(new InetSocketAddress(key.getAddr(), key.getPort()));
        }

        return conn;
    }

    public NioSocketChannel createSocket(InetSocketAddress key) {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        final ChannelFuture channelFuture = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new MyReadHandler())
                .connect(key);

        try {
            channelFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final Channel channel = channelFuture.channel();

        /*final Channel channel = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new MyReadHandler())
                .connect(key)
                .channel();*/

        return (NioSocketChannel) channel;
    }

    public class MyReadHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client register..........");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client active................");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            RpcHeader header = null;
            RpcBody body = null;
            if (byteBuf.readableBytes() >= HEADER_SIZE) {
                header = ReadUtils.readHeader(byteBuf, HEADER_SIZE);
                if (byteBuf.readableBytes() >= header.getBodyDataSize()) {
                    body = ReadUtils.readBody(byteBuf, header.getBodyDataSize());
                    callback.setBody(body);
                    countDownLatch.countDown();
                }
            }
        }


    }

    public class MyHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            RpcBody clientBody = getBody(method, args);
            final byte[] clientBodyBytes = ReadUtils.getObjBytes(clientBody);
            final RpcHeader clientHeader = getHeader(clientBodyBytes.length);
            final byte[] clientHeaderBytes = ReadUtils.getObjBytes(clientHeader);

            System.out.println(clientHeaderBytes.length);

            NioSocketChannel socket = getSocketFromPool(addrPort);
            final ByteBuf clientByteBuf = UnpooledByteBufAllocator.DEFAULT.directBuffer(1024);
            clientByteBuf.writeBytes(clientHeaderBytes);
            clientByteBuf.writeBytes(clientBodyBytes);
            socket.writeAndFlush(clientByteBuf);

            countDownLatch.await();

            return callback.getBody().getServerReturnValue();
        }

        private RpcBody getBody(Method method, Object[] args) {
            RpcBody clientBody = new RpcBody();
            clientBody.setMethodName(method.getName());
            clientBody.setArgs(args);
            clientBody.setArgsType(method.getParameterTypes());

            return clientBody;
        }

        private RpcHeader getHeader(int bodyDataSize) {
            RpcHeader header = new RpcHeader();
            header.setBodyDataSize(bodyDataSize);

            return header;
        }
    }

}
