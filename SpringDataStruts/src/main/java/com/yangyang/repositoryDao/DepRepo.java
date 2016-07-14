package com.yangyang.repositoryDao;

import com.yangyang.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DepRepo extends JpaRepository<Department,Integer>,JpaSpecificationExecutor<Department> {
    //query
}
