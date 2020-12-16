package com.rpc.hz.procotol;

import java.io.Serializable;

/**
 * @author zehua
 * @date 2020/12/12 12:24
 */
public class RpcBody implements Serializable {
    private String methodName;
    private Object[] args;
    private Class<?>[] argsType;

    private Object serverReturnValue;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public Class<?>[] getArgsType() {
        return argsType;
    }

    public void setArgsType(Class<?>[] argsType) {
        this.argsType = argsType;
    }

    public Object getServerReturnValue() {
        return serverReturnValue;
    }

    public void setServerReturnValue(Object serverReturnValue) {
        this.serverReturnValue = serverReturnValue;
    }
}
