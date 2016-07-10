package com.yangyang.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
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
@Component
@Aspect
public class LoggingAspect {

    public void beforeMethod(JoinPoint jp){
        String methodName = jp.getSignature().getName();
        List<Object> args = Arrays.asList(jp.getArgs());
        System.out.println("begin method... "+methodName+" ,args: "+args);
    }

    public void afterMethod(JoinPoint jp ){
        String methodName = jp.getSignature().getName();
        System.out.println("end "+methodName +" ... ");
    }
    public void afterRunning(JoinPoint jp ,Object result){
        String methodName = jp.getSignature().getName();
        System.out.println("end "+methodName +" with --> "+result);
    }

    public void afterThrowing(JoinPoint jp,Exception ex){
        String methodName = jp.getSignature().getName();
        System.out.println(methodName+" occurs a exception: "+ex.getMessage());
    }

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
