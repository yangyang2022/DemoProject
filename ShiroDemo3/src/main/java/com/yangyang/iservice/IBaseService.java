package com.yangyang.iservice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IBaseService<T> {
    void save(T t);
    <S extends T> Iterable<S> save(Iterable<S> entities);

    T findOne(Integer id);
    boolean exists(Integer id);
    Iterable<T> findAll();
    Iterable<T> findAll(Iterable<Integer> ids);
    long count();
    void delete(Integer id);
    void delete(T entity);
    void delete(Iterable<? extends T> entities);
    void deleteAll();

    //分页
    Page<T> findAll(Pageable page);
    Page<T> findAll(Specification<T> spec, Pageable page);
}
