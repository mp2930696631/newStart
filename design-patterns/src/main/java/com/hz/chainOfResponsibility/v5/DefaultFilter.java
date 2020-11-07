package com.hz.chainOfResponsibility.v5;

/**
 * 只是为了开始执行filter链
 *
 * @Auther zehua
 * @Date 2020/11/8 7:10
 */
public class DefaultFilter {
    private FilterChain filterChain;

    public DefaultFilter() {
    }

    public DefaultFilter(FilterChain filterChain) {
        this.filterChain = filterChain;
    }

    public void exec(Request request, Response response) {
        this.filterChain.doFilter(request, response);
    }
}
