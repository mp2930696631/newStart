package com.hz.practice.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: zehua
 * @date: 2020/11/14 14:05
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter init....");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("pre do....");
        chain.doFilter(request, response);
        System.out.println("post do....");
    }

    @Override
    public void destroy() {
        System.out.println("destroy....");
    }
}
