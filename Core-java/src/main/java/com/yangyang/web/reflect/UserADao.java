package com.yangyang.web.reflect;

public class UserADao implements IUserDao{
    @Override
    public void add() {
        System.out.println("A userDao ... ");
    }
    private void setName(){
        System.out.println("hello world");
    }
}
