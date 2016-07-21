package com.yangyang.model;

import com.yangyang.Utils.MyBatisUtil;
import com.yangyang.model.Pager;
import com.yangyang.model.SystemContext;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseDao<T> {
    public BaseDao(){
        DaoFactory.injectDao(this);
    }
    public void add(T obj){
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSession();
            session.insert(obj.getClass().getName()+".add",obj);
            session.commit();
        } catch (IOException e) {
            session.rollback();
        }finally {
            MyBatisUtil.close(session);
        }
    }

    public void update(T obj){
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSession();
            session.update(obj.getClass().getName()+".update",obj);
            session.commit();
        } catch (IOException e) {
            session.rollback();
        }finally {
            MyBatisUtil.close(session);
        }
    }

    public void delete(Class<T> clz,int id){
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSession();
            session.delete(clz.getName()+".delete",id);
            session.commit();
        } catch (IOException e) {
            session.rollback();
        }finally {
            MyBatisUtil.close(session);
        }
    }

    public T load(Class<T> clz,int id){
        SqlSession session = null;
        T t = null;
        try {
            session = MyBatisUtil.createSession();
            t = session.selectOne(clz.getName()+".load",id);
        } catch (IOException e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            MyBatisUtil.close(session);
        }
        return t;
    }
    public T loadBySqlId(String sqlId,Map<String,Object> params){
        SqlSession session = null;
        T t = null;
        try {
            session = MyBatisUtil.createSession();
            t = session.selectOne(sqlId,params);
        } catch (IOException e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            MyBatisUtil.close(session);
        }
        return t;
    }
    public T loadBySqlId(String sqlId,Object obj){
        SqlSession session = null;
        T t = null;
        try {
            session = MyBatisUtil.createSession();
            t = session.selectOne(sqlId,obj);
        } catch (IOException e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            MyBatisUtil.close(session);
        }
        return t;
    }
    //不带分页
    public List<T> list(Class<T> clz,Map<String,Object> params){
        return this.list(clz.getName()+".list",params);
    }
    public List<T> list(String sqlId,Map<String,Object> params){
        List<T> list = null;
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSession();
            list = session.selectList(sqlId,params);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            MyBatisUtil.close(session);
        }
        return list;
    }
    //带分页
    public Pager<T> find(Class<T> clz, Map<String,Object> params){
        return this.find(clz.getName()+".find",params);
    }
    public Pager<T> find(String sqlId, Map<String,Object> params){
        int pageSize = SystemContext.getPageSize();
        int pageOffset = SystemContext.getPageOffset();
        //int pageSize = 10;
        //int pageOffset = 0;

        String order = SystemContext.getOrder();
        String sort = SystemContext.getSort();

        Pager<T> pagers = new Pager<>();
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSession();
            if(params == null ) params = new HashMap<>();

            params.put("pageSize",pageSize);
            params.put("pageOffset",pageOffset);
            params.put("order",order);
            params.put("sort",sort);

            List<T> users = session.selectList(sqlId,params);

            pagers.setDatas(users);
            pagers.setPageOffset(pageOffset);
            pagers.setPageSize(pageSize);

            int totalRecord = session.selectOne(sqlId+"_count",params);
            pagers.setTotalRecord(totalRecord);

        } catch (IOException e) {
        } finally {
            MyBatisUtil.close(session);
        }
        return pagers;
    }
}
