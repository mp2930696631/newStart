package com.hz.servlet;

import com.hz.Servlet;
import com.hz.Utils;

/**
 * @author zehua
 * @date 2021/2/21 11:04
 */
public class MyServlet extends Servlet {
    static {
        System.out.println("project01......"+MyServlet.class.getClassLoader());
    }
    @Override
    public void service() {
        Utils.print();
    }
}
