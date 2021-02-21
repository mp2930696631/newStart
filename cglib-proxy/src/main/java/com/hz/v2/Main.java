package com.hz.v2;

import com.hz.v2.callback.InterfaceCallback;
import com.hz.v2.callback.ObjectCallback;
import com.hz.v2.callbackFilter.MyCallbackFilter;
import com.hz.v2.proxied.MyInterface;
import com.hz.v2.proxied.MyObject;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Constructor;

/**
 * @author zehua
 * @date 2021/2/18 11:37
 * <p>
 * 设置callback数组的四种方式
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

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyObject.class);
        enhancer.setInterfaces(new Class[]{MyInterface.class});
        enhancer.setCallbackFilter(callbackFilter);
        enhancer.setCallbackTypes(callbackClasses);

        // 方式一：
        /*enhancer.setCallbacks(callbacks);
        final Object o = enhancer.create();*/

        // 方式二：
        /*final Class aClass = enhancer.createClass();
        final Constructor declaredConstructor = aClass.getDeclaredConstructor();
        final Object o = declaredConstructor.newInstance();
        ((Factory) o).setCallbacks(callbacks);*/

        // 方式三：
        /*final Class aClass = enhancer.createClass();
        Enhancer.registerStaticCallbacks(aClass, callbacks);
        final Constructor declaredConstructor = aClass.getDeclaredConstructor();
        final Object o = declaredConstructor.newInstance();*/

        // 方式四：
        final Class aClass = enhancer.createClass();
        Enhancer.registerCallbacks(aClass, callbacks);
        final Constructor declaredConstructor = aClass.getDeclaredConstructor();
        final Object o = declaredConstructor.newInstance();


        if (o instanceof MyObject) {
            System.out.println(((MyObject) o).getObjectName());
        }
        System.out.println(".....................");
        if (o instanceof MyInterface) {
            System.out.println(((MyInterface) o).getIName());
        }
    }

}
