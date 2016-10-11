package com.yangyang.dao.daoImpl;


import com.yangyang.dao.idao.IBaseDao;
import com.yangyang.model.Pager;
import com.yangyang.systemContext.SystemContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@SuppressWarnings("unchecked")
public class BaseDao<T> implements IBaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;
    private Class<T> clz;

    protected Session getSession(){ return sessionFactory.getCurrentSession();}

    public Class<T> getClz() {
        if(clz==null) {
            clz = ((Class<T>)
                    (((ParameterizedType)(this.getClass().getGenericSuperclass())).getActualTypeArguments()[0]));
        }
        return clz;
    }

    @Override
    public void add(T t) {
        this.getSession().save(t);
    }

    @Override
    public void addObj(Object obj) {
        this.getSession().save(obj);
    }

    @Override
    public void update(T t) {
        this.getSession().update(t);
    }

    @Override
    public void updateObj(Object obj) {
        this.getSession().update(obj);
    }

    @Override
    public void delete(int id) {
        this.getSession().delete(this.load(id));
    }

    @Override
    public void deleteObj(Object obj) {
        this.getSession().delete(obj);
    }

    @Override
    public T load(int id) {
        return this.getSession().load(getClz(),id);
    }

    protected Query setParameter(String hql,Object[] args){
        Query q = this.getSession().createQuery(hql);
        if(args != null){
            for (int i = 0; i < args.length; ++i) {
                q.setParameter(i, args[i]);
            }
        }
        return q;
    }
    private String getCountHql(String hql){
        String s = hql.substring(0,hql.indexOf("from"));
        if(s == null || s.trim().equals("")){
            hql = "select count(*) "+hql;
        }else {
            hql = hql.replace(s,"select count(*) ");
        }
        if(hql.contains("fetch")) hql = hql.replace("fetch","");
        return hql;
    }
    @Override
    public List<T> list(String hql, Object[] args) {

        return setParameter(hql,args).list();
    }

    @Override
    public List<T> list(String hql) {
        return this.list(hql,null);
    }

    @Override
    public List<T> list(String hql,Object arg) {
        return this.list(hql,new Object[]{arg});
    }

    @Override
    public List<Object> listByObj(String hql, Object[] args) {

        return setParameter(hql,args).list();
    }

    @Override
    public List<Object> listByObj(String hql) {
        return this.listByObj(hql);
    }

    @Override
    public List<Object> listByObj(String hql, Object arg) {
        return this.listByObj(hql,new Object[]{arg});
    }

    @Override
    public Pager<T> find(String hql, Object[] args) {
        int pageSize = SystemContext.getPageSize();
        int pageOffset = SystemContext.getPageOffset();

        if(pageSize < 0 ) pageSize = 10;
        if(pageOffset < 0 ) pageOffset = 0;

        Query q = setParameter(hql,args);
        q.setFirstResult(pageOffset).setMaxResults(pageSize);

        Query cq = setParameter(getCountHql(hql),args);

        Pager<T> pager = new Pager<T>();

        pager.setPageSize(pageSize);
        pager.setPageOffet(pageOffset);
        pager.setDatas(q.list());
        pager.setTotalRecord((Long) cq.uniqueResult());

        return pager;
    }

    @Override
    public Pager<T> find(String hql) {
        return this.find(hql,null);
    }

    @Override
    public Pager<T> find(String hql,Object arg) {
        return this.find(hql,new Object[]{arg});
    }

    @Override
    public Pager<Object> findByObj(String hql, Object[] args) {
        int pageSize = SystemContext.getPageSize();
        int pageOffset = SystemContext.getPageOffset();

        if(pageSize < 0 ) pageSize = 10;
        if(pageOffset < 0 ) pageOffset = 0;

        Query q = setParameter(hql,args);
        q.setFirstResult(pageOffset).setMaxResults(pageSize);

        Query cq = setParameter(getCountHql(hql),args);

        Pager<Object> pager = new Pager<>();
        pager.setPageSize(pageSize);
        pager.setPageOffet(pageOffset);
        pager.setDatas(q.list());
        pager.setTotalRecord((Long) cq.uniqueResult());

        return pager;
    }

    @Override
    public Pager<Object> findByObj(String hql) {
        return this.findByObj(hql);
    }

    @Override
    public Pager<Object> findByObj(String hql, Object arg) {
        return this.findByObj(hql,new Object[]{arg});
    }

    @Override
    public Object queryForHql(String hql, Object[] args) {
        return setParameter(hql,args).uniqueResult();
    }

    @Override
    public Object queryForHql(String hql) {
        return this.queryForHql(hql);
    }

    @Override
    public Object queryForHql(String hql,Object args) {
        return this.queryForHql(hql,new Object[]{args});
    }

    @Override
    public void executeByHql(String hql, Object[] args) {
        Query q = setParameter(hql,args);
        q.executeUpdate();
    }

    @Override
    public void executeByHql(String hql) {
        this.executeByHql(hql);
    }

    @Override
    public void executeByHql(String hql,Object args) {
        this.executeByHql(hql,new Object[]{args});
    }
}
