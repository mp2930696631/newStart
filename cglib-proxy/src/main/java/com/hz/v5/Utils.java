package com.hz.v5;

import com.hz.v5.advice.AdviceI;
import com.hz.v5.advice.impl.*;
import com.hz.v5.anno.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;

/**
 * @author zehua
 * @date 2021/2/18 16:03
 */
public class Utils {

    public static AdviceI[] getAdvices() {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.hz.v5.MyAspect");
            final Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            final Object o = declaredConstructor.newInstance();
            final Method before = aClass.getDeclaredMethod("before");
            final Method after = aClass.getDeclaredMethod("after");
            final Method afterReturing = aClass.getDeclaredMethod("afterReturing");
            final Method afterThrowing = aClass.getDeclaredMethod("afterThrowing");
            final Method around = aClass.getDeclaredMethod("around", MethodChain.class);

            BeforeAdvice beforeAdvice = new BeforeAdvice(before, null, o);
            AfterAdvice afterAdvice = new AfterAdvice(after, null, o);
            AfterReturningAdvice afterReturningAdvice = new AfterReturningAdvice(afterReturing, null, o);
            AfterThrowingAdvice afterThrowingAdvice = new AfterThrowingAdvice(afterThrowing, null, o);
            AroundAdvice aroundAdvice = new AroundAdvice(around, null, o);

            // AdviceI[] adviceIS = new AdviceI[]{beforeAdvice, aroundAdvice, afterAdvice, afterReturningAdvice, afterThrowingAdvice};
            AdviceI[] adviceIS = new AdviceI[]{beforeAdvice, afterAdvice, afterReturningAdvice, afterThrowingAdvice, aroundAdvice};
            return adviceIS;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static AdviceI[] getAdvicesByAnno() {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.hz.v5.MyAspect");
            final Constructor<?> declaredConstructor = aClass.getDeclaredConstructor();
            final Object o = declaredConstructor.newInstance();

            final Method[] declaredMethods = aClass.getDeclaredMethods();
            ArrayList<AdviceI> al = new ArrayList();
            for (Method declaredMethod : declaredMethods) {
                final AdviceI advice = findAdvice(declaredMethod, null, o);
                al.add(advice);
            }

            AdviceI[] adviceIS = new AdviceI[al.size()];
            for (int i = 0; i < al.size(); i++) {
                adviceIS[i] = al.get(i);
            }
            return adviceIS;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /*public static void main(String[] args) {
        getAdvicesByAnno();
    }*/

    private static AdviceI findAdvice(Method method, Object[] args, Object instance) {
        final Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType() == Before.class) {
                return new BeforeAdvice(method, args, instance);
            } else if (annotation.annotationType() == After.class) {
                return new AfterAdvice(method, args, instance);
            } else if (annotation.annotationType() == AfterReturning.class) {
                return new AfterReturningAdvice(method, args, instance);
            } else if (annotation.annotationType() == AfterThrowing.class) {
                return new AfterThrowingAdvice(method, args, instance);
            } else if (annotation.annotationType() == Around.class) {
                return new AroundAdvice(method, args, instance);
            }
        }

        return null;
    }
}
