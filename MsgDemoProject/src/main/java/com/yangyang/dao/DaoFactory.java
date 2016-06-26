package com.yangyang.dao;

public class DaoFactory {
    public static IUserDao getUserDao(){
        return new UserDao();
    }
    public static ICommentDao getCommentDao(){
        return new CommentDao();
    }
    public static IMessageDao getMsgDao(){
        return new MessageDao();
    }

}
