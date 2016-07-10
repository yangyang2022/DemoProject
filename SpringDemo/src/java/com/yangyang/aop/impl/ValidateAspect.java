package com.yangyang.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Order(1) //数字越小 优先级越高
@Aspect
@Component
public class ValidateAspect {

    /**
     * 定义一个方法,声明切入点表达式,一般的该声明方法里面没有其他代码
     */
    @Pointcut("execution(* com.yangyang.aop.impl.*.*(..))")
    public void declareExpression(){}

    @Before("declareExpression()")
    public void validateArgs(JoinPoint jp){
        System.out.println("validate args: "+ Arrays.asList(jp.getArgs()));
    }

    @After("declareExpression()")
    public void afterValidate(JoinPoint jp){
        System.out.println("validate end ");
    }
}
