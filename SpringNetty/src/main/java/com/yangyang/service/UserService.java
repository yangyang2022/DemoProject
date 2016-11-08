package com.yangyang.service;


import org.springframework.stereotype.Component;

@Component
public class UserService implements IUserService{

    @Override
    public void login() {
        System.out.println("login ...");
    }

    @Override
    public void getInfo() {
        System.out.println("getInfo ...");
    }
}
