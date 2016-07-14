package com.yangyang.Iservice;

import com.yangyang.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface IPersonService extends IBaseService<Person> {

    public void updatePersonEmail(String email,Integer id);

    void save(List<Person> ps);

    Page<Person> findAll(Pageable pageable);

    //带查询条件的分页
    Page<Person> findAll(Specification<Person> spec, Pageable pageable);

    public void testPerson();
}
