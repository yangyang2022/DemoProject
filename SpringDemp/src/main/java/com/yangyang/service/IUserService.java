package com.yangyang.service;

import com.yangyang.model.User;

public interface IUserService {

    void add(User user);
    void update(User user);
    User load(int id);
    void delete(int id);
}
