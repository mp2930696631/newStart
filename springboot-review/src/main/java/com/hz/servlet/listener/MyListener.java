package com.hz.servlet.listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author zehua
 * @date 2020/11/24 13:20
 */
// @WebListener
public class MyListener implements HttpSessionListener, ServletRequestListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session crested...");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session destroy....");


    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println("request init......");
    }
}
