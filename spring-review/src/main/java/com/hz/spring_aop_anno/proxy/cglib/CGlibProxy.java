package com.hz.spring_aop_anno.proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther zehua
 * @Date 2020/11/6 15:12
 */
public class CGlibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println(method.getName() + "方法被调用.....");
        Object invoke = proxy.invokeSuper(obj, args);
        System.out.println("结果为：" + invoke);

        return 100;
    }
}
