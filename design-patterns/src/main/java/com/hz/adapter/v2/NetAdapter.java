package com.hz.adapter.v2;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:17
 */
public class NetAdapter implements USBAdapter {
    private NetLine netLine;

    public NetAdapter() {

    }

    public NetAdapter(NetLine netLine) {
        this.netLine = netLine;
    }

    public void hander() {
        netLine.request();
    }
}
