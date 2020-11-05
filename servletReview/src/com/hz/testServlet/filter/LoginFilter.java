package com.hz.testServlet.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Auther zehua
 * @Date 2020/11/5 19:02
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("login filter init....");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("login filter destroy......");
    }
}
