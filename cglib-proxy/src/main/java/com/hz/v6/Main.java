package com.hz.v6;

import com.hz.v6.callback.InterfaceCallback;
import com.hz.v6.callback.ObjectCallback;
import com.hz.v6.callbackFilter.MyCallbackFilter;
import com.hz.v6.other.MyNamePolicy;
import com.hz.v6.other.MyStrategy;
import com.hz.v6.proxied.MyConf;
import com.hz.v6.proxied.MyInterface;
import com.hz.v6.proxied.MyObject;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.core.GeneratorStrategy;
import net.sf.cglib.core.NamingPolicy;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * @author zehua
 * @date 2021/2/18 11:37
 *
 * 模拟@Configuration修饰的类
 */
public class Main {

    public static void main(String[] args) throws Exception {
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\zehua\\proxyTest");
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
        enhancer.setSuperclass(MyConf.class);
        enhancer.setInterfaces(new Class[]{MyInterface.class});
        enhancer.setCallbackFilter(callbackFilter);
        enhancer.setCallbackTypes(callbackClasses);
        enhancer.setNamingPolicy(namingPolicy);
        enhancer.setStrategy(strategy);

        final Class aClass = enhancer.createClass();
        final Constructor declaredConstructor = aClass.getDeclaredConstructor();
        final Object o = declaredConstructor.newInstance();
        ((Factory) o).setCallbacks(callbacks);

        if (o instanceof MyConf) {
            ((MyConf) o).m1();
        }

        System.out.println("分界线----------------------------------");

        // 接下来使用反射为myConf中的myObject属性赋值，虽然代理对象是Myconf的子类，但是也可以这么做
        final Field myObject = aClass.getSuperclass().getDeclaredField("myObject");
        myObject.setAccessible(true);
        myObject.set(o, new MyObject());
        if (o instanceof MyConf) {
            ((MyConf) o).m1();
        }
        /*System.out.println(".....................");
        if (o instanceof MyInterface) {
            ((MyInterface) o).setIName("MyInterface");
        }*/
    }

}
