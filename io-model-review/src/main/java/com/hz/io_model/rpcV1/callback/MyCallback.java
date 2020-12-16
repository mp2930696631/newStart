package com.hz.io_model.rpcV1.callback;

import com.hz.io_model.rpcV1.protocol.MyBody;

import java.util.concurrent.CountDownLatch;

/**
 * @author zehua
 * @date 2020/12/12 10:49
 */
public class MyCallback {
    private MyBody body;
    private CountDownLatch countDownLatch;

    public MyCallback() {

    }

    public MyBody getBody() {
        return body;
    }

    public void setBody(MyBody body) {
        this.body = body;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public void setCountDownLatch(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }
}
