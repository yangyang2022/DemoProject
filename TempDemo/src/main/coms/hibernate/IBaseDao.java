package com.yangyang.dao.idao;

import java.util.List;

public interface IBaseDao <T>{
    void add(T t);
    void delete(int id);
    void update(T t);
    T load(int id);
    List<T> list(String sql,Object[] args);
    List<T> listAll();
}
