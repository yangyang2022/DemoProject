package com.yangyang.service;

import com.yangyang.Iservice.IUserService;
import com.yangyang.model.User;
import com.yangyang.repositoryDao.UserRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements IUserService{

    @Resource
    private UserRepo userRepo;

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public <S extends User> Iterable<S> save(Iterable<S> entities) {
        return userRepo.save(entities);
    }

    @Override
    public User findOne(Integer id) {
        return userRepo.findOne(id);
    }

    @Override
    public boolean exists(Integer id) {
        return userRepo.exists(id);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Iterable<User> findAll(Iterable<Integer> ids) {
        return findAll(ids);
    }

    @Override
    public long count() {
        return userRepo.count();
    }

    @Override
    public void delete(Integer id) {
        userRepo.delete(id);
    }

    @Override
    public void delete(User entity) {
        userRepo.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends User> entities) {
        userRepo.delete(entities);
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public Page<User> findAll(Pageable page) {
        return userRepo.findAll(page);
    }

    @Override
    public Page<User> findAll(Specification<User> spec, Pageable page) {
        return userRepo.findAll(spec,page);
    }
}
