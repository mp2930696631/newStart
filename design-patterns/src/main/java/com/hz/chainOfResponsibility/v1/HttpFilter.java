package com.hz.chainOfResponsibility.v1;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:49
 */
public class HttpFilter implements Filter {
    private Msg msg;

    public HttpFilter() {

    }

    public HttpFilter(Msg msg) {
        this.msg = msg;
    }

    @Override
    public void doFilter() {
        String msgStr = msg.getStr();
        msgStr = msgStr.replace("<", "[");
        msgStr = msgStr.replace(">", "]");
        msg.setStr(msgStr);

    }
}
