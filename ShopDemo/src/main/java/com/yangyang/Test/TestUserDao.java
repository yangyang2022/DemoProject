package com.yangyang.Test;

import com.yangyang.dao.IUserDao;
import com.yangyang.dao.DaoFactory;
import com.yangyang.mode.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestUserDao {
    private IUserDao userDao;
    @Before
    public void setUp(){
        userDao = DaoFactory.getUserDao();
    }
    @Test
    public void testUserAdd() {
        User user = new User("adminy","adminx","超级管理员");
        userDao.add(user);
        //for (int i = 0; i < 5; ++i) {
        //    User user = new User("yangyang "+i,"admin","超级管理员");
        //    userDao.add(user);
        //}
    }

    @Test
    public void testUserDelete() {
        userDao.delete(70);
    }

    @Test
    public void testUserUpdate() {
        User user = new User(6,"xx","xx","xx");
        userDao.update(user);
    }

    @Test
    public void testUserLoad() {
        System.out.println(userDao.load(10));
    }

    @Test
    public void testUserlist() {
        List<User> users = userDao.list();
        users.forEach(System.out::println);
    }

    @Test
    public void testUserLogin() {
        User user = userDao.login("admin","admin");
        System.out.println(user);

    }
}
