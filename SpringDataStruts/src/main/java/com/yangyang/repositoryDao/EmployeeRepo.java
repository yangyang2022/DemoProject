package com.yangyang.repositoryDao;

import com.yangyang.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmployeeRepo extends JpaRepository<Employee,Integer>,JpaSpecificationExecutor<Employee> {

    Employee findByLastname(String lastname);
}
