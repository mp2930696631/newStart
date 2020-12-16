package com.hz.io_model.testNetty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author zehua
 * @date 2020/12/12 5:23
 */
public class ServerNetty {

    /*public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        NioServerSocketChannel serverSocket = new NioServerSocketChannel();
        final ChannelPipeline pipeline = serverSocket.pipeline();
        pipeline.addLast(new MyAcceptHandler(nioEventLoopGroup));

        nioEventLoopGroup.register(serverSocket);

        serverSocket.bind(new InetSocketAddress("192.168.41.1", 9090));


        serverSocket.closeFuture().sync();
    }*/

    public static void main(String[] args) throws InterruptedException {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        final Channel channel = serverBootstrap.group(group, group)
                .channel(NioServerSocketChannel.class)
                // acceptHandler 可以自己写，也可以使用serverBootstrap自带的
                // .handler(new MyAcceptHandler(group))
                .childHandler(new MyReadHandler())
                .bind(new InetSocketAddress("192.168.41.1", 9090)).channel();
        // channel.closeFuture().sync();
    }

    public static class MyAcceptHandler extends ChannelInboundHandlerAdapter {
        NioEventLoopGroup group;

        public MyAcceptHandler(NioEventLoopGroup group) {
            this.group = group;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("server register.......");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("server active...........");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            NioSocketChannel socket = (NioSocketChannel) msg;
            group.register(socket);
            final ChannelPipeline pipeline = socket.pipeline();
            pipeline.addLast(new MyReadHandler());

            socket.writeAndFlush(Unpooled.copiedBuffer("hello cilent".getBytes()));
        }
    }

    public static class MyReadHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("socket register...........");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("socket active..........");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf byteBuf = (ByteBuf) msg;
            final CharSequence charSequence = byteBuf.getCharSequence(0, byteBuf.readableBytes(), StandardCharsets.UTF_8);
            final String s = charSequence.toString();
            System.out.println(s);
            final Channel channel = ctx.channel();

            channel.writeAndFlush(byteBuf);
        }
    }

}
