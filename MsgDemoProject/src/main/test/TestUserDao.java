package com.yangyang.Test;

import com.yangyang.Utils.NameUtil;
import com.yangyang.model.DaoFactory;
import com.yangyang.model.IUserDao;
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

        //User user = new User("adminy","adminx","超级管理员");
        User user = null;
        List<String> names = NameUtil.getNames(500);
        for (int i = 0; i < 500; ++i) {
            user = new User("user"+i,"123",names.get(i),0,0);
            userDao.add(user);
        }
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
        //Pager<User> pagers = userDao.list("",10,1);
        //
        //List<User> users = pagers.getDatas();
        //
        //users.forEach(System.out::println);
    }

    @Test
    public void testUserLogin() {
        User user = userDao.login("admin","admin");
        System.out.println(user);
    }

    @Test
    public void testUserListCon() {
        //List<User> ul = userDao.list("ad",1,1);
        //System.out.println(ul.size());
    }
}
