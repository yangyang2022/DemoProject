package com.yangyang.Iservice;

import com.yangyang.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface IUserService extends IBaseService<User>{

    User update(User user);

    //分页
    Page<User> findAll(Pageable page);
    Page<User> findAll(Specification<User> spec,Pageable page);
}
