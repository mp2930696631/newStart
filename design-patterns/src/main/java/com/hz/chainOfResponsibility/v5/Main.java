package com.hz.chainOfResponsibility.v5;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 完全模拟servlet的拦截器
 * 引入反射机制，预先加载所有的filter，Mapping类
 *
 * @Auther zehua
 * @Date 2020/11/8 6:34
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ArrayList<String> mappingAl = Mapping.al;
        ArrayList<Filter> fcAl = new ArrayList<>();
        for (int i = 0; i < mappingAl.size(); i++) {
            String classname = mappingAl.get(i);
            Class<Filter> filterClass = (Class<Filter>) Class.forName(classname);
            Filter filter = filterClass.getConstructor().newInstance();
            fcAl.add(filter);
        }

        FilterChain fc = new FilterChain();
        Class<FilterChain> filterChainClass = FilterChain.class;
        Field fieldAl = filterChainClass.getDeclaredField("al");
        fieldAl.setAccessible(true);
        fieldAl.set(fc, fcAl);

        Request request = new Request();
        Response response = new Response();
        DefaultFilter defaultFilter = new DefaultFilter(fc);
        defaultFilter.exec(request, response);
        System.out.println(request.str);
        System.out.println(response.str);
    }

}
