package com.yangyang.dao.daoimpl;

import com.yangyang.dao.idao.IBaseDao;
import com.yangyang.model.Pager;
import com.yangyang.systemContext.SystemContext;
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
    public List<T> list(String sql) {
        return this.list(sql,null);
    }

    @Override
    public List<T> list(String sql, Object arg) {
        return list(sql,new Object[]{arg});
    }

    @Override
    public Pager<T> find(String sql, Object[] args) {

        Pager<T> pagers = new Pager<T>();

        int pageSize = SystemContext.getPageSize();
        int pageOffset = SystemContext.getPageOffset();

        Query query = this.getSession().createQuery(sql);
        Query cq = this.getSession().createQuery(getCountSql(sql));
        if(args != null){
            for (int i = 0; i < args.length; ++i) {
                query.setParameter(i,args[i]);
                cq.setParameter(i,args[i]);
            }
        }
        query.setFirstResult(pageOffset);
        query.setMaxResults(pageSize);

        long count = (long) cq.uniqueResult();

        pagers.setData(query.list());
        pagers.setPageSize(pageSize);
        pagers.setPageOffset(pageOffset);

        pagers.setTotalRecord(count);
        return pagers;
    }

    private String getCountSql(String sql){
        sql = "select count(*) "+sql.substring(sql.indexOf("from"));
        // fetch 不能使用count(*)
        sql = sql.replace("fetch","");
        return sql;
    }
    @Override
    public Pager<T> find(String sql, Object args) {
        return this.find(sql,new Object[]{args});
    }

    @Override
    public Pager<T> find(String sql) {
        return this.find(sql,null);
    }
}
