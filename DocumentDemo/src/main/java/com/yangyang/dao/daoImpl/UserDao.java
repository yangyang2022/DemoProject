package com.yangyang.dao.daoImpl;

import com.yangyang.dao.idao.IUserDao;
import com.yangyang.model.User;
import org.hibernate.query.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao extends BaseDao<User> implements IUserDao{
    @Override
    public List<User> listByIds(Integer[] userIds) {
        //Query q = this.getSession().createQuery("select u from User u left join fetch u.department where u.id in (:ids)");
        Query q = this.getSession().createQuery("select new User(u.id,u.nickname) from User u where u.id in (:ids)");
        q.setParameterList("ids",userIds);
        return q.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<User> listAllSendUser(int userId) {
        //先查询这个用户所在部门,然后该部门可发文的部门,然后查询人数
        //String hql = "select new User(tu.id,tu.username) from User u1 left join Depscope ds where (u1.depId=ds.depId) "+
        //        " left join User tu where (tu.depId=ds.department.id) and tu.id=?";

        String sql = "select tu.id,tu.username from t_user t1 left join t_dep_scope ds on (t1.dep_id=ds.dep_id) "
                +" right join t_user tu on (ds.s_dep_id=tu.dep_id) where t1.id="+userId;

        //addEntity 会将关联值设置进去,但是此处department没有意义设置进去
        //return this.getSession().createNativeQuery(sql)
        //        .addEntity(User.class)
        //        .list();
        return this.getSession().createNativeQuery(sql)
                .setResultTransformer(Transformers.aliasToBean(User.class)).list();
    }
}
