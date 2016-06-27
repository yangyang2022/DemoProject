package com.yangyang.intercepter;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class HelloIntecepter extends AbstractInterceptor{

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        System.out.println("system intercepter ... ");
        return actionInvocation.invoke();//往下走
    }
}
