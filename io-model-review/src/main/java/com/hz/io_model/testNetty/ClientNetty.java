package com.hz.io_model.testNetty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedInputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;

/**
 * @author zehua
 * @date 2020/12/12 5:16
 */
public class ClientNetty {

    /*public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(1);
        NioSocketChannel socket = new NioSocketChannel();
        final ChannelPipeline pipeline = socket.pipeline();
        pipeline.addLast(new MyReadHandler());
        nioEventLoopGroup.register(socket);
        socket.connect(new InetSocketAddress("192.168.41.1", 9090)).sync();

    }*/

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        bootstrap.channel(NioSocketChannel.class)
                .group(group)
                .handler(new MyReadHandler())
                .connect(new InetSocketAddress("192.168.41.1", 9090));
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

            BufferedInputStream inputStream = new BufferedInputStream(System.in);
            byte[] buffer = new byte[1024];
            final int read = inputStream.read(buffer);
            byteBuf.clear();
            channel.writeAndFlush(byteBuf.writeBytes(buffer, 0, read));
        }


    }

}
