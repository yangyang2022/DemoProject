package com.yangyang.proxy;

import com.yangyang.log.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

@Component("logAspectXML")
public class LogAspectXML {
    public void start(JoinPoint jp){
        System.out.println(jp.getTarget());
        System.out.println(jp.getSignature().getName());
        Logger.info("start 方法调用开始 ... ");
    }

    public void end(JoinPoint jp){
        Logger.info("end 方法调用结束 ... ");
    }

    public void round(ProceedingJoinPoint pjp) throws Throwable {
        Logger.info("开始 around  ... ");
        pjp.proceed();
        Logger.info("结束 around ... ");
    }
}
