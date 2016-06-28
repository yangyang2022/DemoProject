package com.yangyang.dao;

import org.apache.log4j.Logger;

public class UserDao {
    public static final Logger logger = Logger.getLogger(UserDao.class);
    public void add(){
        //为日志 设定不同的级别,log4j: debug < info < warn <error < fatal
        logger.debug("添加了用户信息 -- UserDao ");
        logger.info("添加了用户信息 -- UserDao ");
        logger.warn("添加了用户信息 -- UserDao ");
        logger.error("添加了用户信息 -- UserDao ");
        logger.fatal("添加了用户信息 -- UserDao  ");
    }
}
