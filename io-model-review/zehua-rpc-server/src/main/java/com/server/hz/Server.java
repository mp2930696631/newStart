package com.server.hz;

import com.rpc.hz.ReadUtils;
import com.rpc.hz.procotol.RpcBody;
import com.rpc.hz.procotol.RpcHeader;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;

/**
 * @author zehua
 * @date 2020/12/12 15:33
 */
public class Server {
    private static final int HEADER_SIZE = 69;

    public static void main(String[] args) {
        ServiceList serviceList = new ServiceList();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        serverBootstrap.group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        final ChannelPipeline pipeline = ch.pipeline();
                        pipeline.remove(ChannelInitializer.class);
                        pipeline.addLast(new MyReadHandler(serviceList));
                    }
                })
                .bind(new InetSocketAddress("192.168.41.1", 9090));
    }

    public static class MyReadHandler extends ChannelInboundHandlerAdapter {
        private ServiceList serviceList;

        public MyReadHandler(ServiceList serviceList) {
            this.serviceList = serviceList;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("socket register.........");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("socket active.............");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("server begin to read....");
            ByteBuf byteBuf = (ByteBuf) msg;
            RpcHeader header = null;
            RpcBody body = null;
            if (byteBuf.readableBytes() >= HEADER_SIZE) {
                header = ReadUtils.readHeader(byteBuf, HEADER_SIZE);
                if (byteBuf.readableBytes() >= header.getBodyDataSize()) {
                    body = ReadUtils.readBody(byteBuf, header.getBodyDataSize());

                    Object returnValue = invockAccordingBody(body, serviceList);
                    RpcBody serverBody = getBody(returnValue);
                    byte[] serverBodyBytes = ReadUtils.getObjBytes(serverBody);
                    RpcHeader serverHeader = getHeader(serverBodyBytes.length);
                    byte[] serverHeaderBytes = ReadUtils.getObjBytes(serverHeader);

                    final ByteBuf serverByteBuf = UnpooledByteBufAllocator.DEFAULT.directBuffer(1024);
                    serverByteBuf.writeBytes(serverHeaderBytes);
                    serverByteBuf.writeBytes(serverBodyBytes);
                    ctx.writeAndFlush(serverByteBuf);
                }
            }
        }

        private RpcBody getBody(Object returnValue) {
            RpcBody body = new RpcBody();
            body.setServerReturnValue(returnValue);

            return body;
        }

        private RpcHeader getHeader(int bodyDataSize) {
            RpcHeader header = new RpcHeader();
            header.setBodyDataSize(bodyDataSize);

            return header;
        }

        private Object invockAccordingBody(RpcBody rpcBody, Object instance) {
            String methodName = rpcBody.getMethodName();
            Object[] args = rpcBody.getArgs();
            Class<?>[] argsType = rpcBody.getArgsType();

            try {
                final Method method = instance.getClass().getMethod(methodName, argsType);
                final Object o = method.invoke(instance, args);

                return o;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

}
