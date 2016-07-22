package com.yangyang.dao.idao;

import com.yangyang.model.Pager;

import java.util.List;

public interface IBaseDao <T>{
    void add(T t);
    void delete(int id);
    void update(T t);
    T load(int id);
    List<T> list(String sql,Object[] args);
    List<T> list(String sql);
    List<T> list(String sql,Object arg);

    Pager<T> find(String sql,Object[] args);
    Pager<T> find(String sql,Object args);
    Pager<T> find(String sql);
}
