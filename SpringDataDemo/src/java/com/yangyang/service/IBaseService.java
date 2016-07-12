package com.yangyang.service;

public interface IBaseService<T> {
    void save(T t);
    T findOne(Integer id);
    boolean exists(Integer id);
    Iterable<T> findAll();
    Iterable<T> findAll(Iterable<Integer> ids);
    long count();
    void delete(Integer id);
    void delete(T entity);
    void delete(Iterable<? extends T> entities);
    void deleteAll();
}
