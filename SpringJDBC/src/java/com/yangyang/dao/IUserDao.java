package com.yangyang.model;

import com.yangyang.model.User;

import java.util.List;

public interface IUserDao {
    void add(User user,int gid);
    void update(User user);
    void delete(int id);
    User load(int id);
    List<User> list(String sql,Object[] args);
}
