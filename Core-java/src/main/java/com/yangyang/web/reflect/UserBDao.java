package com.yangyang.web.reflect;

public class UserBDao implements IUserDao {
    @Override
    public void add() {
        System.out.println("B userBdao ... ");
    }
}
