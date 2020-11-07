package com.hz.chainOfResponsibility.v5;

import java.util.ArrayList;

/**
 * @Auther zehua
 * @Date 2020/11/8 6:39
 */
public class Mapping {
    static ArrayList<String> al = new ArrayList<>();
    static String httpFilterName = "com.hz.chainOfResponsibility.v5.HttpFilter";
    static String sensitiveFilterName = "com.hz.chainOfResponsibility.v5.SensitiveFilter";
    static String smileFilterName = "com.hz.chainOfResponsibility.v5.SmileFilter";

    static {
        al.add(httpFilterName);
        al.add(sensitiveFilterName);
        al.add(smileFilterName);
    }
}
