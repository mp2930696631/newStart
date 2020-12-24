package com.hz.singleLock.exclusive;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.concurrent.locks.LockSupport;

/**
 * 小试牛刀。。
 *
 * @author zehua
 * @date 2020/12/24 6:17
 */
public class ExclusiveLock01 {
    private volatile Node head;
    private volatile Node tail;
    private volatile int state;
    private volatile Thread exclusiveThread;

    public static void main(String[] args) {
        ExclusiveLock01 lock = new ExclusiveLock01();
        lock.addWaiter();
        lock.addWaiter();

    }

    public void lock() {
        if (!tryAcquire(1)) {
            acquireQueued(addWaiter());
        }

    }

    private Node addWaiter() {
        Node current = new Node(Thread.currentThread());

        /*if (tail == null) {
            if (initQueue(current))
                return current;
        }*/


        // 自旋设置，直到成功
        for (; ; ) {
            if (head != null) {
                Node oldNode = tail;
                if (oldNode != null) {
                    if (compareAndSetTail(oldNode, current)) {
                        // 注意，虽然执行compareAndSetTail(oldNode, current)之后，tail=current
                        // 但是，也不能写成oldNode.next = tail;
                        // 因为tail是共享变量，在多线程的环境下，可能该线程在这句代码前停下来了
                        // 所以可能造成很多线程没有入队，永远也得不到唤醒，造成程序一直停不下来
                        oldNode.next = current;
                        return current;
                    }
                }
            } else {
                initQueue();
            }

        }

    }

    // 头结点无用
    private void initQueue() {
        Node h = null;
        if (compareAndSetTail(null, (h = new Node()))) {
            head = h;
        }
    }

    private void acquireQueued(Node work) {
        for (; ; ) {
            //if (head != null) {
            // 不使用双向链表就直接用head.next与当前节点做判断算了
            final Node headNext = head.next;
            if (headNext != null) {
                if (headNext == work && tryAcquire(1)) {
                    head = headNext;
                    head.thread = null;
                    return;
                }
                // 进入队列后休息，等待被唤醒
                LockSupport.park(this);
            }
            //}
        }
    }

    private boolean tryAcquire(int arg) {
        if (compareAndSetState(0, 1)) {
            this.exclusiveThread = Thread.currentThread();
            return true;
        }
        return false;
    }

    public void unlock() {
        if (tryRealse(1)) {
            if (head != null) {
                final Node headNext = head.next;
                if (headNext != null) {
                    LockSupport.unpark(headNext.thread);
                }
            }
        } else {
            System.out.println("unlock failed..............");
        }
    }

    private boolean tryRealse(int arg) {
        if (compareAndSetState(1, 0)) {
            this.exclusiveThread = null;
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

        public Node(Thread thread, Node next) {
            this.thread = thread;
            this.next = next;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }


    private static VarHandle STATE;
    private static VarHandle TAIL;
    private static VarHandle NEXT;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            STATE = l.findVarHandle(ExclusiveLock01.class, "state", int.class);
            TAIL = l.findVarHandle(ExclusiveLock01.class, "tail", Node.class);
            NEXT = l.findVarHandle(Node.class, "next", Node.class);
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

    private boolean compareAndSetNext(Node obj, Node expected, Node update) {
        return NEXT.compareAndSet(obj, expected, update);
    }

}
