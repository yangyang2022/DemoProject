package com.yangyang.dao.idao;

import com.yangyang.model.Department;

import java.util.List;

public interface IDepartmentDao extends IBaseDao<Department> {
    //根据部门获取所有可以发文的ID列表
    List<Integer> listAllExistIds(int depId);
}
