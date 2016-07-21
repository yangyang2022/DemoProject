package com.yangyang.model;

import com.yangyang.Utils.HibernateUtil;
import com.yangyang.model.User;
import org.hibernate.Session;

public class UserDao {
    public void update(User user){
        Session session = null;
        try {
            session = HibernateUtil.openSession();
            session.beginTransaction();
            //User tu = session.load(User.class,user.getId());
            User tu = load(user.getId());
            //由于使用了 load ,有延迟加载,返回时候u是一个代理对象,仅仅只有ID
            //由于此处使用u时,去数据库取时,但是session已经关闭了
            tu.setNickname(user.getNickname());
            tu.setPassword(user.getPassword());
            tu.setUsername(user.getUsername());
            tu.setBorn(user.getBorn());

            session.update(tu);

            session.getTransaction().commit();
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession(session);
        }
    }

    public User load(int id){
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.openSession();

            user = session.get(User.class,id);
            HibernateUtil.closeSession(session);
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            HibernateUtil.closeSession(session);
        }
        return user;
    }
}
