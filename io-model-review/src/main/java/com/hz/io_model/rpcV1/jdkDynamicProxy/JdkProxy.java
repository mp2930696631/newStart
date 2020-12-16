package com.hz.io_model.rpcV1.jdkDynamicProxy;

import com.hz.io_model.rpcV1.callback.MyCallback;
import com.hz.io_model.rpcV1.protocol.MyBody;
import com.hz.io_model.rpcV1.protocol.MyHeader;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/12 9:43
 */
public class JdkProxy<T> {

    public T getInstance(ClassLoader loader, Class<T> interfaces) {
        return (T) Proxy.newProxyInstance(loader, new Class[]{interfaces}, new MyHandler());
    }

    public class MyHandler implements InvocationHandler {

        // 向远程服务器发起通信
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            CountDownLatch countDownLatch = new CountDownLatch(1);

            System.out.println("client begin to write.......");

            MyBody body = new MyBody();
            body.setMethodName(method.getName());
            body.setArgs(args);
            body.setArgsType(method.getParameterTypes());

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(body);
            final byte[] clientBodyBytes = baos.toByteArray();
            int dataSize = clientBodyBytes.length;

            MyHeader header = new MyHeader();
            header.setDataSize(dataSize);

            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(header);
            final byte[] clientHeadBytes = baos.toByteArray();
            System.out.println(clientHeadBytes.length);

            MyCallback callback = new MyCallback();
            callback.setCountDownLatch(countDownLatch);

            Bootstrap bootstrap = new Bootstrap();
            NioEventLoopGroup group = new NioEventLoopGroup(1);
            final ChannelFuture channelFuture = bootstrap.channel(NioSocketChannel.class)
                    .group(group)
                    .handler(new MyReadHandler(callback))
                    .connect(new InetSocketAddress("192.168.41.1", 9090));

            channelFuture.sync();

            final Channel channel = channelFuture.channel();

            NioSocketChannel socket = (NioSocketChannel) channel;

            final ByteBuf byteBuf = UnpooledByteBufAllocator.DEFAULT.directBuffer(1024);
            byteBuf.writeBytes(clientHeadBytes);
            byteBuf.writeBytes(clientBodyBytes);
            socket.writeAndFlush(byteBuf);

            System.out.println("client write over............");

            countDownLatch.await();

            final MyBody returnBody = callback.getBody();

            return returnBody.getReturnValue();
        }
    }

    public class MyReadHandler extends ChannelInboundHandlerAdapter {
        private MyCallback callback;

        public MyReadHandler(MyCallback callback) {
            this.callback = callback;
        }

        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client register.........");
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("client active........");
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println("client begin to read....");
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

                    callback.setBody(body);
                    callback.getCountDownLatch().countDown();
                }
            }
        }
    }

}
