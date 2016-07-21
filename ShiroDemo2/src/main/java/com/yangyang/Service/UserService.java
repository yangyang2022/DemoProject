package com.yangyang.Service;

import com.yangyang.DaoRepo.UserDao;
import com.yangyang.IService.IUserService;
import com.yangyang.model.shiroModel.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class UserService implements IUserService{

    @Resource
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public Set<String> getRoles(String username) {
        return userDao.getRoles(username);
    }

    @Override
    public Set<String> getPerms(String username) {
        return userDao.getPerms(username);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public <S extends User> Iterable<S> save(Iterable<S> entities) {
        return userDao.save(entities);
    }

    @Override
    public User findOne(Integer id) {
        return (User) userDao.findOne(id);
    }

    @Override
    public boolean exists(Integer id) {
        return userDao.exists(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Iterable<User> findAll(Iterable<Integer> ids) {
        return userDao.findAll(ids);
    }

    @Override
    public long count() {
        return userDao.count();
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    public void delete(User entity) {
        userDao.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends User> entities) {
        userDao.delete(entities);
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return userDao.findAll(page);
    }

    @Override
    public Page<User> findAll(Specification<User> spec, Pageable page) {
        return userDao.findAll(spec,page);
    }
}
