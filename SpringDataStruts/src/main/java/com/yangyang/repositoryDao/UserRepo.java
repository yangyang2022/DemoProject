package com.yangyang.repositoryDao;

import com.yangyang.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User> {
    //code your query
}
