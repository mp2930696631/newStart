package com.rpc.hz.entity;

/**
 * @author zehua
 * @date 2020/12/12 13:17
 */
public class AddrPort {
    private String addr;
    private int port;

    public AddrPort() {
    }

    public AddrPort(String addr, int port) {
        this.addr = addr;
        this.port = port;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object obj) {
        AddrPort ap = (AddrPort) obj;

        return this.addr.equals(ap.addr) && this.port == ap.port;
    }
}
