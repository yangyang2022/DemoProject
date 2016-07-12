package com.yangyang.repositoryDao;

import com.yangyang.model.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepo extends CrudRepository<Person,Integer>{

    Person getByLastname(String lastname);

    //where lastname like %? and id < ?
    List<Person> getByLastnameStartingWith(String name);

    @Query("select p from Person p where p.id = (select max(p1.id) from Person p1)")
    Person getMaxIdPerson();

    @Query("select p from Person p where p.lastname = ?1 and p.email = ?2 ")
    List<Person> getPersonByUsername(String lastname,String email);

    @Query("select p from Person p where p.lastname = :lastname and p.email = :email ")
    List<Person> getPersonByUsername2(@Param("lastname") String lastname,@Param("email") String email);

    @Query("select p from Person p where p.lastname like %?1% or p.email like %?2%")
    List<Person> getPersonlike(String lastname,String email);

    @Query("select p from Person p where p.lastname like %:lastname% or p.email like %:email%")
    List<Person> getPersonlike2(@Param("lastname") String lastname,@Param("email") String email);

    @Query(value = "select count(id) from t_person",nativeQuery = true)
    int getTotleCount();

    @Query("select p from Person p ")
    List<Person> getAll();

}
