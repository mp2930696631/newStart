package com.hz.v5;

import com.hz.v5.anno.*;

/**
 * @author zehua
 * @date 2021/2/18 16:04
 */
// @Aspect
public class MyAspect {
    @Before
    public void before() {
        System.out.println("Before...");
    }

    @After
    public void after() {
        System.out.println("after...");
    }

    @AfterReturning
    public void afterReturing() {
        System.out.println("afterReturing....");
    }

    @AfterThrowing
    public void afterThrowing() {
        System.out.println("afterThrowing....");
    }

    @Around
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
