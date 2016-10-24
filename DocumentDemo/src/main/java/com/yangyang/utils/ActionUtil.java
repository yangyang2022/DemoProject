package com.yangyang.utils;

import com.opensymphony.xwork2.ActionContext;

public class ActionUtil {

    public static final String REDIRECT = "redirect";
    public static final String LOGIN = "login";

    public static final String REDIRECT_DEP_LIST = "department_list";
    public static final String REDIRECT_USER_LIST = "user_list";
    public static final String REDIRECT_MSG_LISTSEND = "message_listSend";
    public static final String REDIRECT_MSG_RECIVE = "message_listRecive";

    public static void setContextData(String name,Object value){
        ActionContext.getContext().put(name,value);
    }
    public static void setContextUrl(String url){
        setContextData("url",url);
    }

    public static void setSessionData(String name, Object value){
        ActionContext.getContext().getSession().put(name,value);
    }
    public static Object getSessionDate(Object key){
        return ActionContext.getContext().getSession().get(key);
    }
}
