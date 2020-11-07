package com.hz.chainOfResponsibility.v3;

/**
 * 组合任意的FilterChain使之变成一条链
 *
 * @Auther zehua
 * @Date 2020/11/8 5:55
 */
@SuppressWarnings("all")
public class Main {

    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setStr("<script>hello world:), zehua.com,996");
        HttpFilter httpFilter = new HttpFilter(msg);
        SensitiveFilter sensitiveFilter = new SensitiveFilter(msg);
        FilterChain fc1 = new FilterChain();
        fc1.addFilter(httpFilter).addFilter(sensitiveFilter).doFilter();

        SmileFilter smileFilter = new SmileFilter(msg);
        FilterChain fc2 = new FilterChain();
        fc2.addFilter(smileFilter);

        fc1.addFilter(fc2).doFilter();
        System.out.println(msg.getStr());
    }

}
