package com.hz.chainOfResponsibility.v2;

/**
 * 使用FilerChain将所有的filter保存在一个list中，调用FilterChain的doFilter的方法时执行
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
        FilterChain fc = new FilterChain();
        fc.addFilter(httpFilter).addFilter(sensitiveFilter).doFilter();
        System.out.println(msg.getStr());
    }

}
