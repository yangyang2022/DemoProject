package com.yangyang.web.Test;

import com.yangyang.web.Util.DaoUtil;
import com.yangyang.web.reflect.*;
import org.junit.Test;

public class TestFactory extends BaseTest{
    private IUserDao userADao;
    private IUserDao userBDao;

    private IUserDao ud;

    public IUserDao getUd() {
        return ud;
    }

    @ShopDI("userADao")
    public void setUd(IUserDao ud) {
        this.ud = ud;
    }

    public IUserDao getUserADao() {
        return userADao;
    }

    @ShopDI
    public void setUserADao(IUserDao userADao) {
        this.userADao = userADao;
    }

    public IUserDao getUserBDao() {
        return userBDao;
    }
    @ShopDI
    public void setUserBDao(IUserDao userBDao) {
        this.userBDao = userBDao;
    }

    @Test
    public void testFac1() {
        IUserDao userADao1 = (UserADao) DaoUtil.createDaoFactory().getDao("userADao");
        IUserDao userADao2 = (UserADao)DaoUtil.createDaoFactory().getDao("userADao");
        userADao1.add();
        System.out.println(userADao1 == userADao2);

        IUserDao userBDao = (UserBDao)DaoUtil.createDaoFactory().getDao("userBDao");
        userBDao.add();
    }

    @Test
    public void testAddressDao() {
        new AddressDao().add();
    }

    @Test
    public void testUserDao() {
        userADao.add();
        userBDao.add();
        ud.add();
    }
}
