package com.hz.v6.callback;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 12:16
 */
public class InterfaceCallback implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("invock interface..");
        final Class<?> aClass = obj.getClass();
        final Field $$iName = aClass.getDeclaredField("$$iName");
        $$iName.setAccessible(true);
        $$iName.set(obj, args[0]);
        return null;
    }
}
