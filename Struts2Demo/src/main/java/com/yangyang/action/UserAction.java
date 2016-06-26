package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

public class UserAction {
    private static final String SUCCESS = "success";
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String add(){
        return "r_list";
    }
    public String list(){
        //method 1 直接在属性里面设置值
        this.setUsername("yangyang");
        this.setPassword("123123");

        //method 2 通过actionContext设置值
        ActionContext.getContext().put("aaa","yangayngaaa");
        ActionContext.getContext().put("bbb",123123);
        //method 3 servletApi 传值
        ServletActionContext.getRequest().setAttribute("req","hello requset");
        return SUCCESS;
    }
    public String addInput(){
        System.out.println(username+" : "+password);
        return SUCCESS;
    }
}
