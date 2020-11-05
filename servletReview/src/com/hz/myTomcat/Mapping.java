package com.hz.myTomcat;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther zehua
 * @Date 2020/11/5 19:54
 */
public class Mapping {
    private static Map<String, String> map = new HashMap<>();

    static {
        map.put("/myTomcat", "com.hz.myTomcat.MyServlet");
    }

    public String getClassName(String pattern) {
        return map.get(pattern);
    }

}
