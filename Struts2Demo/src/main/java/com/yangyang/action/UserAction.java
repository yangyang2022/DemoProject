package com.yangyang.action;

import com.opensymphony.xwork2.ActionContext;
import com.yangyang.model.Department;
import com.yangyang.model.User;
import org.apache.struts2.ServletActionContext;

import java.util.ArrayList;
import java.util.List;

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

        User u = new User(1,"老沈","123123");
        Department dep = new Department("saohuang");
        u.setDep(dep);
        ActionContext.getContext().getValueStack().push(u);//push into root

        //method 3 servletApi 传值
        ServletActionContext.getRequest().setAttribute("req","hello requset");
        return SUCCESS;
    }
    public String addInput(){
        System.out.println(username+" : "+password);
        return SUCCESS;
    }
    public String show(){
        this.setUsername("习近平");
        ActionContext.getContext().put("age",33);

        List<User> users = new ArrayList<>();

        users.add(new User(1,"唐僧","123123"));
        users.add(new User(2,"悟空","123123"));
        users.add(new User(3,"八戒","123123"));
        users.add(new User(4,"沙僧","123123"));
        users.add(new User(5,"洋洋","123123"));

        ActionContext.getContext().put("us",users);

        return SUCCESS;
    }
    public String demo(){
        System.out.println("demo test ... ");
        return SUCCESS;
    }
}
