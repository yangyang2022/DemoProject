package com.yangyang.service;

import com.yangyang.dao.IUserDao;
import com.yangyang.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//@Component("userService")
@Service //等于Service("userService")
public class UserService implements IUserService {
    private IUserDao userDao;
    private List<String> names;

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public IUserDao getUserDao() {
        return userDao;
    }

    // 不建议使用 autowire 注入 ,它是使用 类型注入
    // 默认使用 名称 注入 在JSR330 中 使用 @inject 注入
    @Resource(name = "userProxyDao")
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
