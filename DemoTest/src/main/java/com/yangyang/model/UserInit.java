package com.yangyang.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by syy on 2016/6/13.
 */
public class UserInit {

    public static List<User> initUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User(1,"wukong","wukong"));
        users.add(new User(2,"bajie","bajie"));
        users.add(new User(3,"shashen","shashen"));
        users.add(new User(4,"tanshen","tanshen"));
        return users;
    }
}
