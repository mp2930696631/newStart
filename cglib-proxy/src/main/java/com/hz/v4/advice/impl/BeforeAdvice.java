package com.hz.v4.advice.impl;

import com.hz.v4.MethodChain;
import com.hz.v4.advice.AdviceI;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 15:20
 */
public class BeforeAdvice implements AdviceI {
    private Method method;
    private Object[] args;
    private Object instance;

    public BeforeAdvice() {

    }

    public BeforeAdvice(Method method, Object[] args, Object instance) {
        this.method = method;
        this.args = args;
        this.instance = instance;
    }

    @Override
    public Object invoke(MethodChain mc) throws Exception {
        this.method.invoke(this.instance, this.args);
        final Object o = mc.process();
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
