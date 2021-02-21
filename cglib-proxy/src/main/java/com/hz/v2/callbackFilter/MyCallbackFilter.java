package com.hz.v2.callbackFilter;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 12:16
 */
public class MyCallbackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        final Class<?> declaringClass = method.getDeclaringClass();
        if (declaringClass.isInterface()) {
            return 1;
        } else if (declaringClass == Object.class) {
            return 2;
        } else {
            return 0;
        }
    }
}
