package com.yangyang.model;

import org.apache.log4j.Logger;

public class UserService {

    //不是 rootlogger 而是一个 test logger ,会去找 log4j.logger.test 的 logger
    //public static final Logger logger = Logger.getLogger("test");
    public static final Logger logger = Logger.getLogger(UserService.class);
    public void add(){
        logger.debug("添加了用户信息 -- debug ");
        logger.info("添加了用户信息 -- info ");
        logger.warn("添加了用户信息 -- warn ");
        logger.error("添加了用户信息 -- error");
        logger.fatal("添加了用户信息 -- fatal ");
    }
}
