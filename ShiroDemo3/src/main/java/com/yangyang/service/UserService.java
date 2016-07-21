package com.yangyang.service;

import com.yangyang.dao.UserDaoRepo;
import com.yangyang.iservice.IUserService;
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
    private UserDaoRepo userDaoRepo;
    
    @Override
    public User getByUsername(String username) {
        return userDaoRepo.getByUsername(username);
    }

    @Override
    public Set<String> getRoles(String username) {
        return userDaoRepo.getRoles(username);
    }

    @Override
    public Set<String> getPerms(String username) {
        return userDaoRepo.getPerms(username);
    }

    @Override
    public void save(User user) {
        userDaoRepo.save(user);
    }

    @Override
    public <S extends User> Iterable<S> save(Iterable<S> entities) {
        return userDaoRepo.save(entities);
    }

    @Override
    public User findOne(Integer id) {
        return (User) userDaoRepo.findOne(id);
    }

    @Override
    public boolean exists(Integer id) {
        return userDaoRepo.exists(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userDaoRepo.findAll();
    }

    @Override
    public Iterable<User> findAll(Iterable<Integer> ids) {
        return userDaoRepo.findAll(ids);
    }

    @Override
    public long count() {
        return userDaoRepo.count();
    }

    @Override
    public void delete(Integer id) {
        userDaoRepo.delete(id);
    }

    @Override
    public void delete(User entity) {
        userDaoRepo.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends User> entities) {
        userDaoRepo.delete(entities);
    }

    @Override
    public void deleteAll() {
        userDaoRepo.deleteAll();
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return userDaoRepo.findAll(page);
    }

    @Override
    public Page<User> findAll(Specification<User> spec, Pageable page) {
        return userDaoRepo.findAll(spec,page);
    }
}
