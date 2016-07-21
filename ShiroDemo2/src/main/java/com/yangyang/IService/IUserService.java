package com.yangyang.IService;

import com.yangyang.model.shiroModel.User;

import java.util.Set;

public interface IUserService extends IBaseService<User>{
    User getByUsername(String username);

    Set<String> getRoles(String username);

    Set<String> getPerms(String username);
}
