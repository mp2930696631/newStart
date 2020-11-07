package com.hz.chainOfResponsibility.v5;

import java.util.ArrayList;

/**
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
