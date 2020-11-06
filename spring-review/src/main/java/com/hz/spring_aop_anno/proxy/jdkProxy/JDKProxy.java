package com.hz.spring_aop_anno.proxy.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther zehua
 * @Date 2020/11/6 13:48
 */
public class JDKProxy {
    private Object instance;

    public JDKProxy(Object instance) {
        this.instance = instance;
    }

    public Object getProxy() {
        Class<?> aClass = this.instance.getClass();
        ClassLoader classLoader = aClass.getClassLoader();
        Class<?>[] interfaces = aClass.getInterfaces();

        Object o = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(method.getName() + "方法 开始执行.....");
                Object invoke = method.invoke(instance, args);
                System.out.println("执行结果是：" + invoke);
                return invoke;
            }
        });

        return o;
    }


}
