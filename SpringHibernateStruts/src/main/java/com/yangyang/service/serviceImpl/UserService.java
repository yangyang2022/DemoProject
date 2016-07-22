package com.yangyang.service.serviceImpl;

import com.yangyang.dao.idao.IGroupDao;
import com.yangyang.dao.idao.IUserDao;
import com.yangyang.exception.UserException;
import com.yangyang.model.Group;
import com.yangyang.model.Pager;
import com.yangyang.model.User;
import com.yangyang.service.iservice.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Resource
    private IUserDao userDao;
    @Resource
    private IGroupDao groupDao;

    @Override
    public void add(User user, int gid) {
        Group g = groupDao.load(gid);
        if(g == null){
            throw new UserException("添加用户的组不存在!");
        }
        user.setGroup(g);
        userDao.add(user);
    }

    @Override
    public void update(User user, int gid) {
        Group g = groupDao.load(gid);
        if(g == null){
            throw new UserException("添加用户的组不存在!");
        }
        user.setGroup(g);
        userDao.update(user);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void delete(int id) {
        userDao.delete(id);
    }

    @Override
    public User load(int id) {
        return userDao.load(id);
    }

    @Override
    public List<User> listAll() {
        return userDao.list("from User u left join fetch u.group");
    }

    @Override
    public List<User> listByGroupId(int gid) {
        return userDao.list("select u from User u where u.group.id=?",gid);
    }

    @Override
    public Pager<User> findUser() {
        String sql = "from User u left join fetch u.group";
        return userDao.find(sql);
    }
}
