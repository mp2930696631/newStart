package com.hz.chainOfResponsibility.v5;


/**
 * @Auther zehua
 * @Date 2020/11/8 5:51
 */
public class SensitiveFilter implements Filter {


    @Override
    public void init() {

    }

    @Override
    public void doFilter(Request request, Response response, FilterChain filterChain) {
        request.str = request.str + " do SensitiveFilter ";
        filterChain.doFilter(request, response);
        response.str = response.str + " do SensitiveFilter ";
    }

    @Override
    public void destroy() {

    }
}
