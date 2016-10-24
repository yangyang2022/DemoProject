package com.yangyang.dao.idao;

import com.yangyang.model.User;

import java.util.List;

public interface IUserDao extends IBaseDao<User>{

    //通过一组用户id获取一组用户对象
    List<User> listByIds(Integer[] userIds);

    //获取摸个用户 可以发送信息的用户
    List<User> listAllSendUser(int userId);
}
