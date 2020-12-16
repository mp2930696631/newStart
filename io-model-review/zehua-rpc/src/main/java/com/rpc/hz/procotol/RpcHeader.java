package com.rpc.hz.procotol;

import java.io.Serializable;

/**
 * @author zehua
 * @date 2020/12/12 12:24
 */
public class RpcHeader implements Serializable {
    private int bodyDataSize;

    public int getBodyDataSize() {
        return bodyDataSize;
    }

    public void setBodyDataSize(int bodyDataSize) {
        this.bodyDataSize = bodyDataSize;
    }
}
