package com.hz.io_model.rpcV1.server;

import com.hz.io_model.rpcV1.protocol.MyBody;
import com.hz.io_model.rpcV1.protocol.MyHeader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * @author zehua
 * @date 2020/12/12 10:56
 */
public class RpcServer {

    public static void main(String[] args) {
        RpcServer server = new RpcServer();
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        serverBootstrap
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {

                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new MyReadHandler(server));
                    }
                })
                .group(group, group)
                .bind(new InetSocketAddress("192.168.41.1", 9090))
                .channel();
    }

    public static class MyReadHandler extends ChannelInboundHandlerAdapter {
        RpcServer server;

        public MyReadHandler(RpcServer server) {
            this.server = server;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("socket register.....");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("socket active....");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("server begin to read....");
            ByteBuf byteBuf = (ByteBuf) msg;
            if (byteBuf.readableBytes() >= 75) {
                byte[] headBytes = new byte[75];
                byteBuf.readBytes(headBytes);
                ByteArrayInputStream bais = new ByteArrayInputStream(headBytes);
                ObjectInputStream ois = new ObjectInputStream(bais);
                final MyHeader header = (MyHeader) ois.readObject();
                final int dataSize = header.getDataSize();

                if (byteBuf.readableBytes() >= dataSize) {
                    byte[] bodyBytes = new byte[dataSize];
                    byteBuf.readBytes(bodyBytes);
                    bais = new ByteArrayInputStream(bodyBytes);
                    ois = new ObjectInputStream(bais);
                    final MyBody body = (MyBody) ois.readObject();
                    String methodName = body.getMethodName();
                    Object[] args = body.getArgs();
                    Class<?>[] argsType = body.getArgsType();
                    final Method method = server.getClass().getMethod(methodName, argsType);
                    final Object o = method.invoke(server, args);

                    MyBody serverBody = new MyBody();
                    serverBody.setReturnValue(o);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    ObjectOutputStream oos = new ObjectOutputStream(baos);
                    oos.writeObject(serverBody);
                    final byte[] serverBodyBytes = baos.toByteArray();

                    MyHeader serverHead = new MyHeader();
                    serverHead.setDataSize(serverBodyBytes.length);
                    baos = new ByteArrayOutputStream();
                    oos = new ObjectOutputStream(baos);
                    oos.writeObject(serverHead);
                    final byte[] serverHeadBytes = baos.toByteArray();

                    final ByteBuf returnByteBuf = UnpooledByteBufAllocator.DEFAULT.directBuffer(1024);
                    returnByteBuf.writeBytes(serverHeadBytes);
                    returnByteBuf.writeBytes(serverBodyBytes);
                    ctx.writeAndFlush(returnByteBuf);
                }
            }
        }
    }

    public String getService(String str) {
        return "invock from rpc-------client param: " + str;
    }

}
