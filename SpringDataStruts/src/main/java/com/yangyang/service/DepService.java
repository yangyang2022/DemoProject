package com.yangyang.service;

import com.yangyang.Iservice.IDepService;
import com.yangyang.model.Department;
import com.yangyang.repositoryDao.DepRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DepService implements IDepService{

    @Resource
    private DepRepo depRepo;

    @Override
    public void save(Department department) {
        depRepo.save(department);
    }

    @Override
    public <S extends Department> Iterable<S> save(Iterable<S> entities) {
        return depRepo.save(entities);
    }

    @Override
    public Department findOne(Integer id) {
        return depRepo.findOne(id);
    }

    @Override
    public boolean exists(Integer id) {
        return depRepo.exists(id);
    }

    @Override
    public Iterable<Department> findAll() {
        return depRepo.findAll();
    }

    @Override
    public Iterable<Department> findAll(Iterable<Integer> ids) {
        return depRepo.findAll(ids);
    }

    @Override
    public long count() {
        return depRepo.count();
    }

    @Override
    public void delete(Integer id) {
        depRepo.delete(id);
    }

    @Override
    public void delete(Department entity) {
        depRepo.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends Department> entities) {
        depRepo.delete(entities);
    }

    @Override
    public void deleteAll() {
        depRepo.deleteAll();
    }

    @Override
    public Page<Department> findAll(Pageable page) {
        return depRepo.findAll(page);
    }

    @Override
    public Page<Department> findAll(Specification<Department> spec, Pageable page) {
        return depRepo.findAll(spec,page);
    }
}
