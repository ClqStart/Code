package com.kuang.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class AnnotationPointCut {

    @Before("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void before(){
        System.out.println("================方法3=======方法执行前==============");
    }

    @After("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public void After(){
        System.out.println("===============方法3========方法执行后==============");
    }

    @Around("execution(* com.kuang.service.UserServiceImpl.*(..))")
    public  void around(ProceedingJoinPoint js) throws Throwable {
        System.out.println("开始");
        Object proceed = js.proceed();
        System.out.println("环绕后");
        System.out.println(proceed);
    }
}
