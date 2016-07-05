package com.yangyang.springdata;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class PersonRepositryImpl implements PersonDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void test() {
        Person person = entityManager.find(Person.class,4);
        System.out.println("--> "+person);
    }
}
