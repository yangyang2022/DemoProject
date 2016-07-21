package com.yangyang.service;

import com.yangyang.model.IUserDao;
import com.yangyang.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements IUserService {
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
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User load(int id) {
        userDao.load(id);
        return null;
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }
}
