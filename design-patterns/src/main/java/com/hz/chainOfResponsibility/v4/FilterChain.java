package com.hz.chainOfResponsibility.v4;

import java.util.ArrayList;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:59
 */
public class FilterChain implements Filter {
    ArrayList<Filter> al = new ArrayList<>();

    public FilterChain addFilter(Filter filter) {
        al.add(filter);

        return this;
    }

    public boolean doFilter() {
        // 对象方法引用相当于filter->filter.doFilter()
        for (int i = 0; i < al.size(); i++) {
            Filter filter = al.get(i);
            boolean flag = filter.doFilter();
            if (!flag) {
                return false;
            }
        }

        return true;
    }
}
