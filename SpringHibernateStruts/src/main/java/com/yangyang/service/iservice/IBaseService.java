package com.yangyang.service.iservice;

import java.util.List;

public interface IBaseService<T> {
    void add(T t);
    void update(T t);
    void delete(int id);
    T load(int id);
    List<T> listAll();
}
