package com.yangyang.dao;

import com.yangyang.model.Pager;
import com.yangyang.model.User;

public interface IUserDao {
    void add(User user);
    void delete(int id);
    void update(User user);
    User loadByUsername(String username);
    User load(int id);
    Pager<User> find(String name);
    User login(String username,String password);

}
