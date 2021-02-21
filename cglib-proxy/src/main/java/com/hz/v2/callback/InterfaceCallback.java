package com.hz.v2.callback;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 12:16
 */
public class InterfaceCallback implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("invock interface..");
        return method.getDeclaringClass().getName();
    }
}
