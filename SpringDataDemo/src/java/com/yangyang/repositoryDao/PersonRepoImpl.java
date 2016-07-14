package com.yangyang.repositoryDao;

import com.yangyang.model.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 注意这里是PersonRepoImpl 不是 PersonDaoImple
 */
public class PersonRepoImpl implements PersonDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void test() {
        Person person = entityManager.find(Person.class,11);
        System.out.println("--> "+person);
    }
}
