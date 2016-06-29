package com.yangyang.dao;

import com.yangyang.model.User;

import java.util.List;

public interface IUserDao {
    void add(User user);
    void update(User user);
    void delete(int id);
    User load(int id);
    List<User> list(String name);
}
