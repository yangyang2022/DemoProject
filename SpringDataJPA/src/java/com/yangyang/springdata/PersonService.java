package com.yangyang.dao;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonService {

    @Resource
    private PersonRepositry personRepositry;

    @Transactional
    public void updatePersonEmail(int id,String email){
        personRepositry.updatepersonEmail(id,email);
    }

    @Transactional
    public void savePersons(List<Person> ps){
        personRepositry.save(ps);
    }
    @Transactional
    public void savePerson(Person person){
        personRepositry.save(person);
    }

    @Transactional
    public void delete(int id){
        personRepositry.delete(id);
    }
    @Transactional
    public void delete(Person person){
        personRepositry.delete(person);
    }
    @Transactional
    public void deleteAll(){
        personRepositry.deleteAll();
    }
}
