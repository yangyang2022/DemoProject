package com.yangyang.model;

import com.yangyang.model.Department;
import com.yangyang.model.Employee;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {
    private static Map<Integer,Employee> employees = null;
    @Resource
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<>();
        employees.put(1001,new Employee(1001,"E-AA","AA@qq.com","男",new Department(101, "D-AA")));
        employees.put(1002,new Employee(1002,"E-BB","BB@qq.com","男",new Department(102, "D-BB")));
        employees.put(1003,new Employee(1003,"E-CC","CC@qq.com","男",new Department(103, "D-CC")));
        employees.put(1004,new Employee(1004,"E-DD","DD@qq.com","男",new Department(104, "D-DD")));
        employees.put(1005,new Employee(1005,"E-EE","EE@qq.com","男",new Department(105, "D-EE")));
    }

    private static Integer initId = 1006;

    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepByID(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee get(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }
}
