package com.hz.singleLock.exclusive;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.locks.LockSupport;

/**
 * 一条铁律：编写多线程程序，不要给共享变量赋值，也不要赋值给共享变量
 * 完美版。。
 *
 * @author zehua
 * @date 2020/12/24 10:39
 */
public class ExclusiveLock {
    private volatile Node head;
    private volatile Node tail;
    private volatile int state;

    public void lock() {
        if (!tryAcquire(1)) {
            acquireQueued(addWaiter());
        }
    }

    private Node addWaiter() {
        Node current = new Node(Thread.currentThread());

        for (; ; ) {
            // 因为head的设置并没有使用cas，所以，这里需要在head设置成功后在进行下一步
            // 因为initQueue中为head赋值的操作并不是原子操作，
            // 在可重入版本中会将head的设置也变成cas操作，就不需要head!=null这个判断了！！！！
            if (head != null) {
                if (tail != null) {
                    Node oldTail = tail;
                    if (compareAndSetTail(oldTail, current)) {
                        oldTail.next = current;
                        return current;
                    }
                }
            } else {
                initQueue();
            }
        }

    }

    private void initQueue() {
        Node h;
        if (compareAndSetTail(null, (h = new Node()))) {
            this.head = h;
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
        if (compareAndSetState(0, 1)) {
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

    // 其实这里并不需要cas操作，因为能进入unlock方法的线程只能有一个，且不可从重入，如果是可重入的话，可能有点不一样！！！
    private boolean tryRelease(int arg) {
        if (compareAndSetState(1, 0)) {
            return true;
        }
        return false;
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

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            TAIL = l.findVarHandle(ExclusiveLock.class, "tail", Node.class);
            STATE = l.findVarHandle(ExclusiveLock.class, "state", int.class);
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

}
