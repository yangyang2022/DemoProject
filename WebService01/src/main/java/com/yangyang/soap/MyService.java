package com.yangyang.soap;

import com.yangyang.service.User;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(endpointInterface = "com.yangyang.soap.IMyService")
public class MyService implements IMyService{

    private static List<User> users = new ArrayList<>();

    public MyService() {
        users.add(new User(1,"admin","123123","超级管理员"));
    }

    @Override
    public int add(int a, int b) {
        System.out.println("a+b= "+(a+b));
        return a+b;
    }

    @Override
    public User adduser(User user) {
        users.add(user);
        return user;
    }

    @Override
    public User login(String username, String password) {
        for(User user : users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> list() {
        return users;
    }
}
