package com.hz.consumer01.filter;

import com.google.common.util.concurrent.RateLimiter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zehua
 * @date 2021/1/14 15:18
 */
@WebFilter
public class LimitFilter implements Filter {
    private RateLimiter limiter;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init consumer01 filter..........");
        limiter = RateLimiter.create(2);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (limiter.tryAcquire()) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            servletResponse.setCharacterEncoding("GB2312");
            servletResponse.getWriter().write("被限流了....from consumer01....");
        }
    }
}
