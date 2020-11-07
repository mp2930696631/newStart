package com.hz.chainOfResponsibility.v5;

import java.util.ArrayList;

/**
 * 需要引入一个指向下一个需要执行的filter的执行，index
 *
 * @Auther zehua
 * @Date 2020/11/8 5:59
 */
public class FilterChain {
    private ArrayList<Filter> al = new ArrayList<>();
    private int index = 0;

    public void doFilter(Request request, Response response) {
        if (index >= al.size()) {
            return;
        }
        Filter filter = al.get(index);
        index++;

        filter.doFilter(request, response, this);
    }
}
