package com.yangyang.model;

public class DaoFactory {
    public static IUserDao getUserDao(){
        return new UserDao();
    }
}
