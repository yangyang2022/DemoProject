package com.yangyang.proxy;

import com.yangyang.log.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;

//@Component("logAspect") //让这个切面类被spring管理
//@Aspect                 //声明这个类是一个切面类
public class LogAspect {

    //execution(* com.yangyang.dao.*.add*(..))
    // 第一个* 返回值 ,第二个* ,所有方法,add* ,add开始的方法 .. 任意参数
    @Before("execution(* com.yangyang.dao.*.add*(..)) ||" +
            "execution(* com.yangyang.dao.*.update*(..)) ")
    public void start(JoinPoint jp){
        //得到执行的对象
        System.out.println(jp.getTarget());
        //得到执行的方法名称
        System.out.println(jp.getSignature().getName());
        Logger.info("start 方法调用结束 ... ");
    }
    @After("execution(* com.yangyang.dao.*.add*(..)) ||" +
            "execution(* com.yangyang.dao.*.update*(..)) ")
    public void end(JoinPoint jp){
        Logger.info("end 方法调用结束 ... ");
    }

    @Around("execution(* com.yangyang.dao.*.add*(..)) ||" +
            "execution(* com.yangyang.dao.*.update*(..)) ")
    public void round(ProceedingJoinPoint pjp) throws Throwable {
        Logger.info("开始 around  ... ");
        pjp.proceed();
        Logger.info("结束 around ... ");
    }

}
