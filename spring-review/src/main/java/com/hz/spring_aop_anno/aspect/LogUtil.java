package com.hz.spring_aop_anno.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 最后使用xml的方式来使用spring的aop，所以下面的注解被注释掉了
 *
 *
 * @Aspect表示这是一个切面类 execution表达式
 * 1、最精确的写法
 * public int com.hz.spring_aop_anno.proxy.MyCalculator.add(int, int)
 * 2、两种通配符
 * *：
 * .
 * 3、最偷懒的方式
 * * *(..)
 * 4、支持逻辑运算符|| && !
 * @Auther zehua
 * @Date 2020/11/6 15:34
 */
//@Aspect
//@Service
public class LogUtil {

    //@Pointcut("execution(* com.hz..divide(..))")
    private void pointCut() {
    }

    //@Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法开始被执行.....111111111111");
    }

    // 因为这个通知无论如何都会被执行，所以相当于放在finally语句中
    //@After("pointCut()")
    public void after(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法执行结束......111111111111");
    }

    //@AfterThrowing(value = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法抛出异常throwing....11111111111...异常信息为：" + e.getMessage());
    }

    //@AfterReturning(value = "pointCut()", returning = "o")
    public void afterReturing(JoinPoint joinPoint, Object o) {
        String name = joinPoint.getSignature().getName();
        System.out.println(name + "方法正常返回returing....111111111111111...结果是：" + o);
    }

    //@Around("pointCut()")
    public Object round(ProceedingJoinPoint proceeding) {
        System.out.println("round before.....111111111111");
        Object proceed = null;
        try {
            proceed = proceeding.proceed();
            System.out.println("round normal.....1111111111");
        } catch (Throwable throwable) {
            System.out.println("round catch ,....111111111111");
        }
        System.out.println("round after finall,....111111111111111");

        return proceed;
    }

}
