package com.hz.v2.callback;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 12:16
 */
public class ObjectCallback implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(method.getDeclaringClass());
        System.out.println("Before=========");
        final Object o = proxy.invokeSuper(obj, args);
        System.out.println("after==========");
        return o;
    }
}
