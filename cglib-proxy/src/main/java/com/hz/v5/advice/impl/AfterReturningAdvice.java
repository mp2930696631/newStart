package com.hz.v5.advice.impl;

import com.hz.v5.MethodChain;
import com.hz.v5.advice.AdviceI;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 15:30
 */
public class AfterReturningAdvice implements AdviceI {
    private Method method;
    private Object[] args;
    private Object instance;

    public AfterReturningAdvice() {
    }

    public AfterReturningAdvice(Method method, Object[] args, Object instance) {
        this.method = method;
        this.args = args;
        this.instance = instance;
    }

    @Override
    public Object invoke(MethodChain mc) throws Exception {
        Object o = mc.process();
        this.method.invoke(this.instance, this.args);

        return o;
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

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }
}
