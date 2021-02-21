package com.hz;

/**
 * @author zehua
 * @date 2021/2/21 11:06
 */
public abstract class Servlet {
    static {
        System.out.println(Servlet.class.getClassLoader());
    }
    public abstract void service();
}
