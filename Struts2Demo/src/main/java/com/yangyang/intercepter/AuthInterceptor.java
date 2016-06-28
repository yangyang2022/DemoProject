package com.yangyang.intercepter;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        //获取地址
        String url = actionInvocation.getProxy().getActionName();
        String username = (String) ActionContext.getContext().getSession().get("loginUser");
        Boolean isAdmin = (Boolean) ActionContext.getContext().getSession().get("isAdmin");

        if(url.startsWith("admin_")){
            if(username == null || "".equals(username.trim())){
                return "loginInput";
            }
            if(isAdmin == null || !isAdmin){
                return "error";
            }
        }else if(url.startsWith("user_")){
            if(username == null || "".equals(username.trim())){
                return "loginInput";
            }
        }
        return actionInvocation.invoke();
    }
}
