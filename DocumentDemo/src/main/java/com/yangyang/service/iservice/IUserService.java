package com.yangyang.service.iservice;

import com.yangyang.model.Pager;
import com.yangyang.model.User;

import java.util.List;

public interface IUserService {
    void add(User user,int depId);
    void delete(int id);
    void update(User user,int depId);
    //不跟新部门
    void update(User user);
    User load(int id);

    //根据部门id获取用户,如果为空获取所有用户
    Pager<User> findUserByDep(Integer depId);

    //用户登录
    User login(String username,String password);

    List<User> listAllSendUser(int userId);
}
