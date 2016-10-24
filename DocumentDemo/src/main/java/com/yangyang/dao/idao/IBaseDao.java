package com.yangyang.dao.idao;

import com.yangyang.model.Pager;

import java.util.List;

public interface IBaseDao<T> {

    public void add(T t);
    public void addObj(Object obj);

    public void update(T t);
    public void updateObj(Object obj);

    public void delete(int id);
    public void deleteObj(Object obj);

    public T load(int id);

    public List<T> list(String hql,Object[] args);
    public List<T> list(String hql);
    public List<T> list(String hql,Object arg);

    public List<Object> listByObj(String hql,Object[] args);
    public List<Object> listByObj(String hql);
    public List<Object> listByObj(String hql,Object arg);

    public Pager<T> find(String hql,Object[] args);
    public Pager<T> find(String hql);
    public Pager<T> find(String hql,Object arg);

    public Pager<Object> findByObj(String hql,Object[] args);
    public Pager<Object> findByObj(String hql);
    public Pager<Object> findByObj(String hql,Object arg);

    //获取一个对象
    public Object queryForHql(String hql,Object[] args);
    public Object queryForHql(String hql);
    public Object queryForHql(String hql,Object args);

    //调用hql更新一组对象
    public void executeByHql(String hql,Object[] args);
    public void executeByHql(String hql);
    public void executeByHql(String hql,Object args);

}
