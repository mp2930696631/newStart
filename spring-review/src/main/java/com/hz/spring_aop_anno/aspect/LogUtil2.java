package com.hz.spring_aop_anno.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Service;

/**
 * @Auther zehua
 * @Date 2020/11/6 17:19
 */
@Aspect
@Service
public class LogUtil2 {
    @Pointcut("execution(* com.hz..divide(..))")
    private void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法开始被执行.....22222222222222");
    }

    // 因为这个通知无论如何都会被执行，所以相当于放在finally语句中
    @After("pointCut()")
    public void after(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法执行结束......22222222222222");
    }

    @AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法抛出异常throwing.......22222222222222异常信息为：" + e.getMessage());
    }

    @AfterReturning(value = "pointCut()", returning = "o")
    public void afterReturing(JoinPoint joinPoint, Object o) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法正常返回returing....22222222222222...结果是：" + o);
    }

    @Around("pointCut()")
    public Object round(ProceedingJoinPoint proceeding) {
        System.out.println("round before...22222222222222..");
        Object proceed = null;
        try {
            proceed = proceeding.proceed();
            System.out.println("round normal...2222222222222222..");
        } catch (Throwable throwable) {
            System.out.println("round catch ,.22222222222222...");
        }
        System.out.println("round after finall,..22222222222222..");

        return proceed;
    }
}
