package com.hz.OnlineCount;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @Auther zehua
 * @Date 2020/11/5 19:21
 */
public class MyListener implements HttpSessionListener, ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("online", 0);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        int count = (int) servletContext.getAttribute("online");
        servletContext.setAttribute("online", ++count);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext servletContext = se.getSession().getServletContext();
        int count = (int) servletContext.getAttribute("online");
        servletContext.setAttribute("online", --count);
    }
}
