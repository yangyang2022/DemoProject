package com.yangyang.model;

import com.yangyang.log.Logger;
import com.yangyang.model.User;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("userProxyDao")
public class UserProxyDao implements IUserDao {
    private IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }
    @Resource
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void add(User user) {
        Logger.info("add a "+user);
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        Logger.info("update a "+user);
        userDao.update(user);
    }

    @Override
    public User load(int id) {
        userDao.load(id);
        return null;
    }

    @Override
    public void delete(int id) {
        Logger.info("add a "+id);
        userDao.delete(id);
    }
}
