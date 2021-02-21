package com.hz.v1;

import com.hz.v1.proxied.MyInterface;
import com.hz.v1.proxied.MyObject;
import com.hz.v1.proxy.MyInterceptor;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @author zehua
 * @date 2021/2/18 11:37
 */
public class Main {

    public static void main(String[] args) {
        // System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "F:\\zehua\\proxyTest");
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(new MyInterceptor());
        enhancer.setSuperclass(MyObject.class);
        enhancer.setInterfaces(new Class[]{MyInterface.class});
        final Object o = enhancer.create();
        if (o instanceof MyObject){
            System.out.println(((MyObject) o).getObjectName());
        }

        if (o instanceof MyInterface){
            System.out.println(((MyInterface) o).getIName());
    }
    }

}
