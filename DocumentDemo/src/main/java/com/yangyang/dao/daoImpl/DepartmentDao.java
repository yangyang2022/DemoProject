package com.yangyang.dao.daoImpl;

import com.yangyang.dao.idao.IDepartmentDao;
import com.yangyang.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentDao extends BaseDao<Department> implements IDepartmentDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<Integer> listAllExistIds(int depId) {
        String hql = "select ds.scopeDep.id from DepScope ds where ds.depId=?";
        return this.setParameter(hql,new Object[]{depId}).list();
    }
}
