package com.yangyang.web.Test;

import com.yangyang.web.Util.DaoUtil;

public class BaseTest {
    public BaseTest(){
        DaoUtil.injectDao(this);
    }
}
