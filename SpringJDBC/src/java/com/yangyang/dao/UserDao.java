package com.yangyang.dao;

import com.yangyang.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("userjdbcDao")
public class UserDao implements IUserDao{

    @Override
    public void add(User user) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User load(int id) {
        return null;
    }

    @Override
    public List<User> list(String name) {
        return null;
    }
}
