package com.yangyang.dao.daoimpl;

import com.yangyang.dao.idao.IBaseDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDao<T> implements IBaseDao<T> {
    @Resource
    private SessionFactory sessionFactory;

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    private Class<T> clz;

    @SuppressWarnings("unchecked")
    public Class<T> getClz() {
        if(clz==null) {
            clz = ((Class<T>)
                    (((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }

    @Override
    public void add(T t) {
        getSession().save(t);
    }

    @Override
    public void delete(int id) {
        getSession().delete(id);
    }

    @Override
    public void update(T t) {
        getSession().update(t);
    }

    @Override
    public T load(int id) {
        return getSession().get(getClz(),id);
    }

    @Override
    public List<T> list(String sql, Object[] args) {
        Query query = getSession().createQuery(sql);
        if(args != null){
            for (int i = 0; i < args.length; ++i) {
                query.setParameter(i,args[i]);
            }
        }
        return query.list();
    }

    @Override
    public List<T> listAll() {
        return list("select t from "+getClz().getSimpleName()+" t",null);
    }
}
