package com.yangyang.dao.daoimpl;

import com.yangyang.dao.idao.IUserDao;
import com.yangyang.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao {
    @Override
    public long countUserByGroupId(int gid) {
        String sql = "select count(*) from User where group.id=:id";
        return (long) this.getSession().createQuery(sql).setParameter("id",gid).uniqueResult();
    }

    @Override
    public void delete(int id) {
        this.getSession().delete(new User(id));
    }

    @Override
    public void deleteByGroupId(int gid) {
        String sql = "delete User where group.id=:id";
        this.getSession().createQuery(sql).setParameter("id",gid).executeUpdate();
    }
}
