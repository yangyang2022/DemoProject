package com.yangyang.web.DemoTest;

public class BaseTest {
    public BaseTest(){
        DaoFactory.injectDao(this);
    }
}
