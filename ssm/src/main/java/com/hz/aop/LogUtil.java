package com.hz.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author zehua
 * @date 2020/11/17 6:05
 */
@Aspect
@Component
public class LogUtil {
    @Pointcut("execution(* com.hz.aop.TestAop.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before..............");
    }
}
