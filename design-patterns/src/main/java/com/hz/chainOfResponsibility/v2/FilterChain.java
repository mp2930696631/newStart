package com.hz.chainOfResponsibility.v2;

import java.util.ArrayList;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:59
 */
public class FilterChain {
    ArrayList<Filter> al = new ArrayList<>();

    public FilterChain addFilter(Filter filter) {
        al.add(filter);

        return this;
    }

    public void doFilter() {
        // 对象方法引用相当于filter->filter.doFilter()
        al.forEach(Filter::doFilter);
    }
}
