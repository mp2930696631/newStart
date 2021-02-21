package com.hz.v5.advice.impl;

import com.hz.v5.MethodChain;
import com.hz.v5.advice.AdviceI;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 15:30
 */
public class AfterThrowingAdvice implements AdviceI {
    private Method method;
    private Object[] args;
    private Object instance;

    public AfterThrowingAdvice() {
    }

    public AfterThrowingAdvice(Method method, Object[] args, Object instance) {
        this.method = method;
        this.args = args;
        this.instance = instance;
    }

    @Override
    public Object invoke(MethodChain mc) throws Exception {
        Object o = null;
        try {
            o = mc.process();
        } catch (Exception e) {
            this.method.invoke(this.instance, this.args);
            throw e;
        }
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
