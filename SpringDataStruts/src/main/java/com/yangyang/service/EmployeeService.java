package com.yangyang.service;

import com.yangyang.Iservice.IEmployeeService;
import com.yangyang.model.Employee;
import com.yangyang.repositoryDao.EmployeeRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmployeeService implements IEmployeeService{

    @Resource
    private EmployeeRepo employeeRepo;

    @Override
    public void save(Employee employee) {
        employeeRepo.save(employee);
    }

    @Override
    public <S extends Employee> Iterable<S> save(Iterable<S> entities) {
        return employeeRepo.save(entities);
    }

    @Override
    public Employee findOne(Integer id) {
        return employeeRepo.findOne(id);
    }

    @Override
    public boolean exists(Integer id) {
        return employeeRepo.exists(id);
    }

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepo.findAll();
    }

    @Override
    public Iterable<Employee> findAll(Iterable<Integer> ids) {
        return employeeRepo.findAll(ids);
    }

    @Override
    public long count() {
        return employeeRepo.count();
    }

    @Override
    public void delete(Integer id) {
        employeeRepo.delete(id);
    }

    @Override
    public void delete(Employee entity) {
        employeeRepo.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends Employee> entities) {
        employeeRepo.delete(entities);
    }

    @Override
    public void deleteAll() {
        employeeRepo.deleteAll();
    }

    @Override
    public Page<Employee> findAll(Pageable page) {
        return employeeRepo.findAll(page);
    }

    @Override
    public Page<Employee> findAll(Specification<Employee> spec, Pageable page) {
        return employeeRepo.findAll(spec,page);
    }

    @Override
    public Employee findByLastname(String lastname) {
        return employeeRepo.findByLastname(lastname);
    }
}
