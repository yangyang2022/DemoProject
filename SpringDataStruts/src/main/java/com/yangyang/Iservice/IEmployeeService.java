package com.yangyang.Iservice;

import com.yangyang.model.Employee;

public interface IEmployeeService extends IBaseService<Employee>{
    //other query
    Employee findByLastname(String username);
}
