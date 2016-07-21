package com.yangyang.service.iservice;

import com.yangyang.model.User;

import java.util.List;

public interface IUserService extends IBaseService<User>{
    void add(User user,int gid);
    List<User> listByGroupId(int gid);
}
