package com.yangyang.service.iservice;

import com.yangyang.model.Department;

import java.util.List;

public interface IDepartmentService {

    void add(Department dep);
    void update(Department dep);
    void delete(int id);
    Department load(int id);
    List<Department> listAllDeps();

    //添加某个部门的管理部门
    void addScopeDep(int depId,int sDepId);

    //为某个部门添加一组管理功能
    void addScopeDeps(int depId,int[] sDepId);

    //删除管理的部门
    void deleteScopeDep(int depId,int sDepId);

    //删除某部门的所有可发的部门
    void deleteScopeDeps(int depId);


    //获取某个部门所能发文的部门
    List<Department> listDepScopeDep(int depId);

    //获取用户所能管理的部门
    List<Department> listUserDep(int userId);

    //获取可发文的所有部门ID
    List<Integer> listDepSocpeDepId(int depId);
}
