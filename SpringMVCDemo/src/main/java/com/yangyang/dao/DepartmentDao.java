package com.yangyang.dao;

import com.yangyang.model.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    private static Map<Integer,Department> deps = null;
    static {
        deps = new HashMap<>();
        deps.put(101,new Department(101,"D-AA"));
        deps.put(102,new Department(102,"D-BB"));
        deps.put(103,new Department(103,"D-CC"));
        deps.put(104,new Department(104,"D-DD"));
        deps.put(105,new Department(105,"D-EE"));
    }

    public Collection<Department> getDeps(){
        return deps.values();
    }

    public Department getDepByID(int id){
        return deps.get(id);
    }
}
