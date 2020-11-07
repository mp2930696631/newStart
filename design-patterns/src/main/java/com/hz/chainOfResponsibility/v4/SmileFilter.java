package com.hz.chainOfResponsibility.v4;

/**
 * @Auther zehua
 * @Date 2020/11/8 6:22
 */
public class SmileFilter implements Filter {
    private Msg msg;

    public SmileFilter() {

    }

    public SmileFilter(Msg msg) {
        this.msg = msg;
    }

    @Override
    public boolean doFilter() {
        String msgStr = msg.getStr();
        msgStr = msgStr.replace(":)", "^v^");
        msg.setStr(msgStr);

        return true;
    }
}
