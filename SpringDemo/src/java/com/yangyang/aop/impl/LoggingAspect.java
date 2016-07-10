package com.yangyang.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 *  声明为一个切面 1:放入IOC容器中 2: 加入@Aspect 3: 在beans.xml中配置
 *  AspectJ支持5中通知(方法)
 *  1: befor
 *  2: after
 *  3: around 环绕通知,围绕方法执行
 *  4: AfterRunning 在方法返回结果之后执行
 *  5: AfterThrowing 异常通知,在方法抛出异常之后执行
 */
@Order(2)
@Component
@Aspect
public class LoggingAspect {

    /**
     * 声明该方法是一个前置通知,在目标方法开始之前执行
     * Before("execution(public int com.yangyang.aop.impl.Calculator.*(int,int))")
     * Before("execution(* com.yangyang.aop.impl.*.*(..))")
     *
     */
    //@Before("execution(* com.yangyang.aop.impl.*.*(..))")
    @Before("ValidateAspect.declareExpression()") //--> 直接引用其他的pointcut
    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        List<Object> args = Arrays.asList(jp.getArgs());
        System.out.println("begin method... "+methodName+" ,args: "+args);
    }

    // 后置通知:在目标方法执行之后(无论是否发生异常)
    //在后置通知中不能访问目标方法执行的结果,可以在返回通知里面访问

    @After("execution(* com.yangyang.aop.impl.*.*(..))")
    public void afterMethod(JoinPoint jp ){
        String methodName = jp.getSignature().getName();
        System.out.println("end "+methodName +" ... ");
    }
    //返回通知 可以访问方法返回值
    //@AfterReturning(value = "execution(* com.yangyang.aop.impl.*.*(..))",returning = "result")
    public void afterRunning(JoinPoint jp ,Object result){
        String methodName = jp.getSignature().getName();
        System.out.println("end "+methodName +" with --> "+result);
    }

    //@AfterThrowing(value = "execution(* com.yangyang.aop.impl.*.*(..))",throwing = "ex")
    public void afterThrowing(JoinPoint jp,Exception ex){
        String methodName = jp.getSignature().getName();
        System.out.println(methodName+" occurs a exception: "+ex.getMessage());
    }

    /**
     * 环绕参数 ProceedingJoinPoint pjp,类似 动态代理的全过程
     *  pjp 可以决定目标方法是否执行 并且 必须有返回值
     *  最强的但不是最常用的
     */
    //@Around(value = "execution(* com.yangyang.aop.impl.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjp){

        String methodName = pjp.getSignature().getName();
        Object result = null;
        //执行目标方法
        try {
            //前置通知
            System.out.println("the method "+methodName+" begin with: "+Arrays.asList(pjp.getArgs()));
            result = pjp.proceed();
            //返回通知
            System.out.println("the method "+methodName+ " ending ... result: "+result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("后置通知: the method "+methodName+ " end");
        return result;
    }
}
