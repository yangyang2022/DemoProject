package com.yangyang.dao;

public class DaoFactory {
    public static IUserDao getUserDao(){
        return new UserDao();
    }
}
