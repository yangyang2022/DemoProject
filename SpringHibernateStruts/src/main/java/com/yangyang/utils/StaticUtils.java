package com.yangyang.utils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class StaticUtils extends ActionSupport{

    public static final String REDIRECT="redirect";
    public static final String USER_LIST="user_list";
    public static final String GROUP_LIST="group_list";

    public static boolean checkEmpty(String str){
        return str == null || "".equals(str.trim());
    }

    public static void setContextData(String name,Object value){
        ActionContext.getContext().put(name,value);
    }
}
