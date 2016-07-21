package com.yangyang.model;

import com.yangyang.model.User;
import org.springframework.stereotype.Repository;

//@Component("userDao")
@Repository // 等于 Repository("userDao")
public class UserDao implements IUserDao{
    @Override
    public void add(User user) {
        System.out.println("add a "+user);
    }

    @Override
    public void update(User user) {
        System.out.println("update a "+user);
    }

    @Override
    public User load(int id) {
        System.out.println("load a user ... ");
        return null;
    }

    @Override
    public void delete(int id) {
        System.out.println("delete a user ... ");
    }
}
