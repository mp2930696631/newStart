package com.hz.chainOfResponsibility.v5;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:48
 */
// @FunctionalInterface
public interface Filter {
    // do nothing 只是为了看起来更像servlet的filter
    void init();

    void doFilter(Request request, Response response, FilterChain filterChain);

    // do nothing 只是为了看起来更像servlet的filter
    void destroy();
}
