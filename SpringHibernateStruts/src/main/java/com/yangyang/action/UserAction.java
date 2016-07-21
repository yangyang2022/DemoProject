package com.yangyang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.model.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<User>{

    private User user;

    public String list(){

        return "list";
    }

    @Override
    public User getModel() {
        if(user == null) user = new User();
        return user;
    }
}
