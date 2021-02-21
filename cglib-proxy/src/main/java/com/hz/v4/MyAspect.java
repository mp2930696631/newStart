package com.hz.v4;

/**
 * @author zehua
 * @date 2021/2/18 16:04
 */
public class MyAspect {
    public void before() {
        System.out.println("Before...");
    }

    public void after() {
        System.out.println("after...");
    }

    public void afterReturing() {
        System.out.println("afterReturing....");
    }

    public void afterThrowing() {
        System.out.println("afterThrowing....");
    }

    public Object around(MethodChain mc) throws Exception {
        System.out.println("round Before...");
        Object proceed = null;
        try {
            proceed = mc.process();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            throw throwable;
        }

        System.out.println("round after...");
        return proceed;
    }
}
