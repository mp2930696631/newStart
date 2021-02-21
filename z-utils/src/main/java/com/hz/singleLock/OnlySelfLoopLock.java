package com.hz.singleLock;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

/**
 * @author zehua
 * @date 2021/2/21 20:27
 */
public class OnlySelfLoopLock {
    private volatile int state = 0;

    public void lock() {
        while (true) {
            if (compareAndSetState(0, 1)) {
                break;
            }
        }
    }

    public void unlock() {
        this.state = 0;
    }

    private static VarHandle STATE;

    static {
        MethodHandles.Lookup l = MethodHandles.lookup();
        try {
            STATE = l.findVarHandle(OnlySelfLoopLock.class, "state", int.class);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private boolean compareAndSetState(int expected, int update) {
        return STATE.compareAndSet(this, expected, update);
    }

}
