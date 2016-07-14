package com.yangyang.Iservice;

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
    void updatePersonEmail(String email,Integer id);
}
