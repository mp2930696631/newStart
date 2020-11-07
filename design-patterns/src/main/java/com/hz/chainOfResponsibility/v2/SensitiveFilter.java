package com.hz.chainOfResponsibility.v2;

/**
 * @Auther zehua
 * @Date 2020/11/8 5:51
 */
public class SensitiveFilter implements Filter {
    private Msg msg;

    public SensitiveFilter() {

    }

    public SensitiveFilter(Msg msg) {
        this.msg = msg;
    }

    @Override
    public void doFilter() {
        String msgStr = msg.getStr();
        msgStr = msgStr.replace("996", "955");
        msg.setStr(msgStr);
    }
}
