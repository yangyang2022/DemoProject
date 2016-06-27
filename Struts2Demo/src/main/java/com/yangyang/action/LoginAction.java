package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
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

    public String input(){

        return "success";
    }
    public String login(){
        if(username.equals("admin")){
            ActionContext.getContext().getSession().put("isAdmin",true);
        }
        ActionContext.getContext().getSession().put("loginUser",username);
        return "articleAdd";
    }
    public String logout(){
        return "success";
    }
}
