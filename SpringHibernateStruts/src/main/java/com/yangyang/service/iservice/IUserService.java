package com.yangyang.service.iservice;

import com.yangyang.model.Pager;
import com.yangyang.model.User;

import java.util.List;

public interface IUserService extends IBaseService<User>{
    void add(User user,int gid);
    void update(User user,int gid);
    List<User> listByGroupId(int gid);
    Pager<User> findUser();
}
