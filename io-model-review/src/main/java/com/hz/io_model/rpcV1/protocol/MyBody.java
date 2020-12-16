package com.hz.io_model.rpcV1.protocol;

import java.io.Serializable;

/**
 * @author zehua
 * @date 2020/12/12 9:54
 */
public class MyBody implements Serializable {
    private String methodName;
    private Object[] args;
    private Class<?>[] argsType;

    private Object returnValue;

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

    public Object getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }
}
