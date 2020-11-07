package com.hz.chainOfResponsibility.v1;

/**
 * 每个Filter都是独立的，没有相互关联，使用时，需要挨个调用其doFilter方法
 *
 * @Auther zehua
 * @Date 2020/11/8 5:55
 */
@SuppressWarnings("all")
public class Main {

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setStr("<script>hello world, zehua.com,996");
        HttpFilter httpFilter = new HttpFilter(msg);
        SensitiveFilter sensitiveFilter = new SensitiveFilter(msg);
        httpFilter.doFilter();
        sensitiveFilter.doFilter();
        System.out.println(msg.getStr());
    }

}
