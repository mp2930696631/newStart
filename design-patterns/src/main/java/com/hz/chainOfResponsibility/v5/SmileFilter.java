package com.hz.chainOfResponsibility.v5;

/**
 * @Auther zehua
 * @Date 2020/11/8 6:22
 */
public class SmileFilter implements Filter {


    @Override
    public void init() {

    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.str = request.str + " do SmileFilter ";
        filterChain.doFilter(request, response);
        response.str = response.str + " do SmileFilter ";
    }

    @Override
    public void destroy() {

    }
}
