package com.hz.v5;

import com.hz.v5.advice.AdviceI;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 16:16
 */
public class ProxyFactory {
    private Object targetInstance;
    private Method method;
    private Object[] args;
    private AdviceI[] adviceIS;

    public ProxyFactory() {
    }

    public Object invoke() throws Exception {
        return this.method.invoke(this.targetInstance, this.args);
    }

    public Object getTargetInstance() {
        return targetInstance;
    }

    public void setTargetInstance(Object targetInstance) {
        this.targetInstance = targetInstance;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public AdviceI[] getAdviceIS() {
        return adviceIS;
    }

    public void setAdviceIS(AdviceI[] adviceIS) {
        this.adviceIS = adviceIS;
    }
}
