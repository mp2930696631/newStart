package com.hz.chainOfResponsibility.v5;


/**
 * @Auther zehua
 * @Date 2020/11/8 5:49
 */
public class HttpFilter implements Filter {

    @Override
    public void init() {

    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.str = request.str + " do httpFilter ";
        filterChain.doFilter(request, response);
        response.str = response.str + " do httpFilter ";
    }

    @Override
    public void destroy() {

    }
}
