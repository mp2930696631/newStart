package com.hz.singleLock.exclusive;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.locks.LockSupport;

/**
 * @author zehua
 * @date 2020/12/24 11:18
 */
public class ReentrantExLock {
    private volatile Node head;
    private volatile Node tail;
    private volatile int state;
    private volatile Thread exclusiveThread;


    public void lockInterruptibly() {

    }

    public void lock() {
        if (!tryAcquire(1)) {
            acquireQueued(addWaiter());
        }
    }

    private Node addWaiter() {
        Node current = new Node(Thread.currentThread());

        for (; ; ) {
            if (tail != null) {
                Node oldTail = tail;
                if (compareAndSetTail(oldTail, current)) {
                    oldTail.next = current;
                    return current;
                }
            } else {
                initQueue();
            }
        }
    }

    private void initQueue() {
        Node n;
        if (compareAndSetHead(null, (n = new Node()))) {
            tail = n;
        }
    }

    private void acquireQueued(Node waiter) {
        for (; ; ) {
            final Node headNext = head.next;
            if (headNext == waiter && tryAcquire(1)) {
                head = headNext;
                head.thread = null;
                return;
            }

            LockSupport.park();
        }
    }

    private boolean tryAcquire(int arg) {
        if (state == 0) {
            if (compareAndSetState(0, 1)) {
                // 如果这个线程在这里停了，恰好是上一个线程在释放锁后又来获取锁
                // 它会走else if，如果我在tryRelease中没有写exclusiveThread = null;
                // 那么他就会进行state++只要执行了这个，就会出大问题
                this.exclusiveThread = Thread.currentThread();
                return true;
            }
        } else if (this.exclusiveThread == Thread.currentThread()) {
            // 同一个线程的话，直接++，不需要cas
            state++;
            return true;
        }

        return false;
    }

    public void unlock() {
        if (tryRelease(1)) {
            doRelease();
        }
    }

    private void doRelease() {
        if (head != null) {
            final Node headNext = head.next;
            if (headNext != null) {
                LockSupport.unpark(headNext.thread);
            }
        }
    }

    // 可重入版本
    private boolean tryRelease(int arg) {
        // 不能直接修改state的值，下面注释掉的写法有问题
        int c = state - arg;
        if (c > 0) {
            // 这里可以不用，因为state不等于零，就算是新来的线程也回去不到锁！！
            state = c;
            return false;
        } else {
            // 这里必须先将exclusiveThread设置为空，否则在tryAcquire会出问题
            exclusiveThread = null;
            state = c;
            return true;
        }

        /*state -= arg;
        // state值得改变和state值得判断分开了，有问题
        if (state > 0) {
            return false;
        } else {
            this.exclusiveThread = null;
            return true;
        }*/
    }


    private static class Node {
        private Thread thread;
        private Node next;

        public Node() {
        }

        public Node(Thread thread) {
            this.thread = thread;
        }
    }

    private static VarHandle TAIL;
    private static VarHandle STATE;
    private static VarHandle HEAD;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            TAIL = l.findVarHandle(ReentrantExLock.class, "tail", Node.class);
            STATE = l.findVarHandle(ReentrantExLock.class, "state", int.class);
            HEAD = l.findVarHandle(ReentrantExLock.class, "head", Node.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean compareAndSetState(int expected, int update) {
        return STATE.compareAndSet(this, expected, update);
    }

    private boolean compareAndSetTail(Node expected, Node update) {
        return TAIL.compareAndSet(this, expected, update);
    }

    private boolean compareAndSetHead(Node expected, Node update) {
        return HEAD.compareAndSet(this, expected, update);
    }

}
