package com.hz.v5.callback;

import com.hz.v5.MethodChain;
import com.hz.v5.ProxyFactory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 12:16
 */
public class ObjectCallback implements MethodInterceptor {
    private ProxyFactory proxyFactory;

    public ObjectCallback() {

    }

    public ObjectCallback(ProxyFactory proxyFactory) {
        this.proxyFactory = proxyFactory;
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        proxyFactory.setMethod(method);
        proxyFactory.setArgs(args);
        MethodChain mc = new MethodChain(proxyFactory.getAdviceIS(), proxyFactory);
        return mc.process();
    }
}
