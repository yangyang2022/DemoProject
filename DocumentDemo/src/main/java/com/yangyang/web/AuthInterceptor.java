package com.yangyang.web;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yangyang.exception.UserException;
import com.yangyang.model.User;
import com.yangyang.utils.ActionUtil;
import com.yangyang.utils.AuthProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("authInterceptor")
public class AuthInterceptor extends AbstractInterceptor {

    @Resource
    private AuthProperty authProperty;

    public boolean checkUrl(String action){
        String[] adminStr = authProperty.getAdminStr().split(",");
        String[] userStr = authProperty.getUserStr().split(",");

        for(String url : userStr){
            if(action.startsWith(url)) return true;
        }
        for(String url : adminStr){
            if(action.startsWith(url)) return false;
        }
        //其他用户也可以访问
        return true;
    }
    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        //1: 获取action的名称
        String an = invocation.getProxy().getActionName();
        if(!an.startsWith("login")){
            User loginUser = (User) ActionUtil.getSessionDate("loginUser");
            if(loginUser == null) return "login";
            //其他权限控制
            if(loginUser.getType() != 1){
                //普通用户
                if(!checkUrl(an))
                    throw new UserException("需要管理员才有权限访问");
            }
        }
        return invocation.invoke();
    }
}
