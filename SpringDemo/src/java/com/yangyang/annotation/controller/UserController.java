package com.yangyang.annotation.controller;

import com.yangyang.annotation.serive.IUserService;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class UserController {
    @Resource
    private IUserService userService;

    public void save(String name){
        userService.save(name);
    }
}
