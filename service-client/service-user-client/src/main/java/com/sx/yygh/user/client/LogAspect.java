package com.sx.yygh.user.client;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
    @Pointcut("@annotation(com.sx.yygh.user.client.SystemLog)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        before(joinPoint);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            System.out.println("finally");
        }
        after(proceed);
        return null;
    }

    private void after(Object proceed) {
        System.out.println("after");

    }

    private void before(ProceedingJoinPoint joinPoint) {
        System.out.println("before");
        System.out.println(joinPoint.getArgs().toString());
    }

}
