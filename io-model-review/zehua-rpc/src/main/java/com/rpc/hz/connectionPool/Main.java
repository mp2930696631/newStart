package com.rpc.hz.connectionPool;

import com.rpc.hz.entity.AddrPort;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author zehua
 * @date 2020/12/12 16:47
 */
public class Main {

    public static void main(String[] args) {
        AddrPort addrPort = new AddrPort("19", 9090);
        SerCliConPool.addItem(addrPort, new NioSocketChannel());
        SerCliConPool.addAddrPort(addrPort);
        final AddrPort addrPort1 = SerCliConPool.getAddrPort("19", 9090);

        System.out.println(SerCliConPool.getConn(addrPort1));
    }

}
