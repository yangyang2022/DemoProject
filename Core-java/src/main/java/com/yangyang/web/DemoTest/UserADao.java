package com.yangyang.web.DemoTest;

public class UserADao implements IUserDao{
    @Override
    public void add() {
        System.out.println("A add ... ");
    }

    @Override
    public void delete() {
        System.out.println("A delete ... ");
    }
}
