package com.xb.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect
@EnableAspectJAutoProxy
public class MyAspect {

    @Before("execution(* com.xb.service.Target.*(..))")
    public void before() {
        System.out.println("before增强方法");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around增强前");
        Object proceed = pjp.proceed();
        System.out.println("around增强后");
        return proceed;
    }

    @AfterReturning("pointcut()")
    public void afterReturning() {
        System.out.println("afterReturning增强方法");
    }

    @Pointcut("execution(* com.xb.service.Target.*(..))")
    public void pointcut() {}
}
