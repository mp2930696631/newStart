package com.hz.io_model.rpcV1.protocol;

import java.io.Serializable;

/**
 * @author zehua
 * @date 2020/12/12 9:53
 */
public class MyHeader implements Serializable {

    private int dataSize;

    public int getDataSize() {
        return dataSize;
    }

    public void setDataSize(int dataSize) {
        this.dataSize = dataSize;
    }
}
