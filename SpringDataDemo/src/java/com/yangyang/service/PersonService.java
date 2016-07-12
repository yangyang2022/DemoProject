package com.yangyang.service;

import com.yangyang.model.Person;
import com.yangyang.repositoryDao.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService implements IBaseService<Person>{

    @Autowired
    private PersonRepo personRepo;

    @Override
    public void save(Person person) {
        personRepo.save(person);
    }

    @Override
    public Person findOne(Integer id) {
        return personRepo.findOne(id);
    }

    @Override
    public boolean exists(Integer id) {
        return personRepo.exists(id);
    }

    @Override
    public Iterable<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public Iterable<Person> findAll(Iterable<Integer> ids) {
        return findAll(ids);
    }

    @Override
    public long count() {
        return personRepo.count();
    }

    @Override
    public void delete(Integer id) {
        personRepo.delete(id);
    }

    @Override
    public void delete(Person entity) {
        personRepo.delete(entity);
    }

    @Override
    public void delete(Iterable<? extends Person> entities) {
        personRepo.delete(entities);
    }

    @Override
    public void deleteAll() {
        personRepo.deleteAll();
    }

    public Person getByLastName(String lastname){
        return personRepo.getByLastname(lastname);
    }
    public List<Person> getAll(){
        return personRepo.getAll();
    }

    public List<Person> getByLastnameStartingWith(String name){
        return personRepo.getByLastnameStartingWith(name);
    }

    public Person getMaxIdPerson(){
        return personRepo.getMaxIdPerson();
    }

    public List<Person> getPersonByUsername(String lastname,String email){
        return personRepo.getPersonByUsername(lastname,email);
    }
    public List<Person> getPersonByUsername2(String email,String lastname){
        return personRepo.getPersonByUsername2(email,lastname);
    }

    public List<Person> getPersonlike(String lastname,String email){
        return personRepo.getPersonlike(lastname,email);
    }
    public List<Person> getPersonlike2(String lastname,String email){
        return personRepo.getPersonlike2(lastname,email);
    }
    public int getTotleCount(){
        return personRepo.getTotleCount();
    }
}
