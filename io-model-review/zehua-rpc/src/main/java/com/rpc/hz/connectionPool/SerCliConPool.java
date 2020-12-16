package com.rpc.hz.connectionPool;

import com.rpc.hz.entity.AddrPort;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zehua
 * @date 2020/12/12 12:13
 */
public class SerCliConPool {
    private static ConcurrentHashMap<AddrPort, NioSocketChannel> map = new ConcurrentHashMap<>();
    private static ArrayList<AddrPort> al = new ArrayList<>();

    public static void addItem(AddrPort key, NioSocketChannel value) {
        map.putIfAbsent(key, value);
    }

    public static NioSocketChannel getConn(AddrPort key) {
        return map.get(key);
    }

    public static boolean isContainKey(AddrPort key) {
        return map.containsKey(key);
    }

    public static void addAddrPort(AddrPort addrPort) {
        final boolean isContains = al.contains(addrPort);
        if (!isContains) {
            al.add(addrPort);
        }
    }

    public static AddrPort getAddrPort(String addr, int port) {
        final int i = al.indexOf(new AddrPort(addr, port));
        return al.get(i);
    }

    public static boolean isContainAddrPort(String addr, int port) {
        AddrPort ap = new AddrPort(addr, port);

        return al.contains(ap);
    }
}
