package com.yangyang.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ValidateAspect {

    public void validateArgs(JoinPoint jp){
        System.out.println("validate args: "+ Arrays.asList(jp.getArgs()));
    }

    public void afterValidate(JoinPoint jp){
        System.out.println("validate end ");
    }
}
