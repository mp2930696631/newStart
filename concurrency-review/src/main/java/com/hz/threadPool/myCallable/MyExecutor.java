package com.hz.threadPool.myCallable;

import java.util.concurrent.locks.LockSupport;

/**
 * @author zehua
 * @date 2020/11/28 14:40
 */
public class MyExecutor<V> {

    private Thread ct = null;

    private StateEnum state;

    private V result = null;

    public MyFuture executor(MyCallable<V> myCallable) {
        this.state = StateEnum.NEW;
        this.ct = Thread.currentThread();
        new Thread(() -> {
            System.out.println("start to call");
            final V result = myCallable.call();
            set(result);
        }).start();

        return new MyFutureImpl();
    }

    private void set(V result) {
        this.result = result;
        this.state = StateEnum.COMPLETED;
        LockSupport.unpark(ct);
    }

    private enum StateEnum {
        NEW(0), COMPLETED(1);

        private int state;

        private StateEnum(int state) {
            this.state = state;
        }
    }

    private class MyFutureImpl<V> implements MyFuture<V> {

        @Override
        public V get() {
            for (; ; ) {
                if (result != null) {
                    return (V) result;
                } else {
                    LockSupport.park();
                }
            }
        }

    }


}
