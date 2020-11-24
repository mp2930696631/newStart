package com.hz.exceptionHandler;

/**
 * @author zehua
 * @date 2020/11/24 11:24
 */
public class ErrorMsg {
    private String msg;
    private int code;

    public ErrorMsg() {
    }

    public ErrorMsg(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ErrorMsg{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                '}';
    }
}
