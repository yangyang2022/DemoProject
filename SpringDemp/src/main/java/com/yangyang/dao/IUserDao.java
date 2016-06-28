package com.yangyang.dao;

import com.yangyang.model.User;

public interface IUserDao {
    void add(User user);
    void update(User user);
    User load(int id);
    void delete(int id);
}
