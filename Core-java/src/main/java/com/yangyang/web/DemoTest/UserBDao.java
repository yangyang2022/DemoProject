package com.yangyang.web.DemoTest;

public class UserBDao implements IUserDao{
    @Override
    public void add() {
        System.out.println("B add ... ");
    }

    @Override
    public void delete() {
        System.out.println("B delete ... ");
    }
}
