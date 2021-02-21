package com.hz.v5;

import com.hz.v5.callback.InterfaceCallback;
import com.hz.v5.callback.ObjectCallback;
import com.hz.v5.callbackFilter.MyCallbackFilter;
import com.hz.v5.other.MyNamePolicy;
import com.hz.v5.other.MyStrategy;
import com.hz.v5.proxied.MyInterface;
import com.hz.v5.proxied.MyObject;
import net.sf.cglib.core.GeneratorStrategy;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Constructor;

/**
 * @author zehua
 * @date 2021/2/18 11:37
 * <p>
 * 模拟spring中的aop，添加了aop拦截链，使用注解
 */
public class Main {

    @SuppressWarnings("all")
    public static void main(String[] args) throws Exception {
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\zehua\\proxyTest");
        // 构建ProxyFactory;
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTargetInstance(new MyObject());
        proxyFactory.setAdviceIS(Utils.getAdvicesByAnno());

        InterfaceCallback interfaceCallback = new InterfaceCallback();
        // 创建ObjectCallback，并传入ProxyFactory
        ObjectCallback objectCallback = new ObjectCallback(proxyFactory);
        // NoOp.INSTANCE如果callback是这个对象，则表示不对相应的方法进行代理
        Callback[] callbacks = new Callback[]{objectCallback, interfaceCallback, NoOp.INSTANCE};
        Class[] callbackClasses = new Class[callbacks.length];
        for (int i = 0; i < callbackClasses.length; i++) {
            callbackClasses[i] = callbacks[i].getClass();
        }
        CallbackFilter callbackFilter = new MyCallbackFilter();

        NamingPolicy namingPolicy = new MyNamePolicy();
        GeneratorStrategy strategy = new MyStrategy();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyObject.class);
        enhancer.setInterfaces(new Class[]{MyInterface.class});
        enhancer.setCallbackFilter(callbackFilter);
        enhancer.setCallbackTypes(callbackClasses);
        enhancer.setNamingPolicy(namingPolicy);
        enhancer.setStrategy(strategy);

        final Class aClass = enhancer.createClass();
        final Constructor declaredConstructor = aClass.getDeclaredConstructor();
        final Object o = declaredConstructor.newInstance();
        ((Factory) o).setCallbacks(callbacks);

        if (o instanceof MyObject) {
            System.out.println(((MyObject) o).getObjectName());
        }
    }

}
