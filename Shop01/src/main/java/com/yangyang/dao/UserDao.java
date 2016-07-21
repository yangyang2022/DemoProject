package com.yangyang.model;

import com.yangyang.model.Pager;
import com.yangyang.model.ShopException;
import com.yangyang.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserDao extends BaseDao<User> implements IUserDao{
    @Override
    public void add(User user) {
        User lu = this.loadByUsername(user.getUsername());
        if(lu != null){
            throw new ShopException("用户已存在!不能重复加入!");
        }
        super.add(user);
    }

    @Override
    public void delete(int id) {
        super.delete(User.class,id);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }

    @Override
    public User loadByUsername(String username) {
        return super.loadBySqlId(User.class.getName()+".load_by_name",username);
    }

    @Override
    public User load(int id) {
        return super.load(User.class,id);
    }

    @Override
    public Pager<User> find(String name) {
        Map<String,Object> params = new HashMap<>();
        if(name != null) params.put("name","%"+name+"%");
        return super.find(User.class,params);
    }

    @Override
    public User login(String username, String password) {
        User u = this.loadByUsername(username);
        if(u == null ){
            throw new ShopException("用户名不存在!");
        }
        if(!u.getPassword().equals(password)){
            throw new ShopException("用户密码不正确!");
        }
        return u;
    }
}
