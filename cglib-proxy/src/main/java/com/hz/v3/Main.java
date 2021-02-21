package com.hz.v3;

import com.hz.v3.callback.InterfaceCallback;
import com.hz.v3.callback.ObjectCallback;
import com.hz.v3.callbackFilter.MyCallbackFilter;
import com.hz.v3.other.MyNamePolicy;
import com.hz.v3.other.MyStrategy;
import com.hz.v3.proxied.MyInterface;
import com.hz.v3.proxied.MyObject;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.core.GeneratorStrategy;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Constructor;

/**
 * @author zehua
 * @date 2021/2/18 11:37
 *
 * 测试enhancer.setNamingPolicy和enhancer.setStrategy
 */
public class Main {

    public static void main(String[] args) throws Exception {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\zehua\\proxyTest");
        InterfaceCallback interfaceCallback = new InterfaceCallback();
        ObjectCallback objectCallback = new ObjectCallback();
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
        System.out.println(".....................");
        if (o instanceof MyInterface) {
            ((MyInterface) o).setIName("MyInterface");
        }
    }

}
