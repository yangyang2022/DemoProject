package com.yangyang.web.reflect;

public class AddressDao {

    private UserADao userADao;
    private UserBDao userBDao;

    public UserBDao getUserBDao() {
        return userBDao;
    }

    public void setUserBDao(UserBDao userBDao) {
        this.userBDao = userBDao;
    }

    public UserADao getUserADao() {
        return userADao;
    }

    public void setUserADao(UserADao userADao) {
        this.userADao = userADao;
    }
    
    public void add(){
        System.out.println("userDao ... ");
        userADao.add();
        userBDao.add();
    }
}
