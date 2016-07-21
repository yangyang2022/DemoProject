package com.yangyang.dao.daoimpl;

import com.yangyang.dao.idao.IGroupDao;
import com.yangyang.model.Group;
import org.springframework.stereotype.Repository;

@Repository
public class GroupDao extends BaseDao<Group> implements IGroupDao {

    @Override
    public void delete(int id) {
        Group g = new Group(id);
        this.getSession().delete(g);
    }
}
