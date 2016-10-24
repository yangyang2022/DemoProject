package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import com.yangyang.service.iservice.IUserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

import static com.yangyang.utils.ActionUtil.*;

@Controller
@Scope("prototype")
public class LoginAction {

    @Resource
    private IUserService userService;
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

    public String loginInput(){

        return LOGIN;
    }
    public String login(){

        setSessionData("loginUser",userService.login(username,password));

        setContextUrl("user_showSelf");
        return REDIRECT;
    }
    public String logout(){
        ActionContext.getContext().getSession().clear();
        setContextUrl("login!loginInput");
        return REDIRECT;
    }
}
