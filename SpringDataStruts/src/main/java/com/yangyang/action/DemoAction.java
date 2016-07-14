package com.yangyang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yangyang.Iservice.IDepService;

import javax.annotation.Resource;

public class DemoAction extends ActionSupport {

    private IDepService depService;
    @Resource
    public void setDepService(IDepService depService) {
        this.depService = depService;
    }

    public String execute(){
        System.out.println("hello world --> "+depService.findAll());
        return "success";
    }
}
