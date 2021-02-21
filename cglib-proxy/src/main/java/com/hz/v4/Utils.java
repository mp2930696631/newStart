package com.hz.v4;

import com.hz.v4.advice.AdviceI;
import com.hz.v4.advice.impl.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @author zehua
 * @date 2021/2/18 16:03
 */
public class Utils {

    public static AdviceI[] getAdvices() {
        Class<?> aClass = null;
        try {
            aClass = Class.forName("com.hz.v4.MyAspect");
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

}
