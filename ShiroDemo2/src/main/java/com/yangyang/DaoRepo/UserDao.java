package com.yangyang.DaoRepo;

import com.yangyang.model.shiroModel.Role;
import com.yangyang.model.shiroModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserDao extends JpaRepository<User,Integer>,JpaSpecificationExecutor<User>{

    User getByUsername(String username);

    @Query("select r.roleName from User u left join Role r on(u.role.id=r.id) where u.username=?1")
    Set<String> getRoles(String username);

    @Query("select p.permName from User u left join Role r on(u.role.id=r.id) left join Permission p on(p.role.id=r.id) where u.username=?1")
    Set<String> getPerms(String username);
}
