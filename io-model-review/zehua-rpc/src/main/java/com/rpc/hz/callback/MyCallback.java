package com.rpc.hz.callback;

import com.rpc.hz.procotol.RpcBody;

import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/12 13:02
 */
public class MyCallback {
    private RpcBody body;
    private CountDownLatch countDownLatch;

    public RpcBody getBody() {
        return body;
    }

    public void setBody(RpcBody body) {
        this.body = body;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
