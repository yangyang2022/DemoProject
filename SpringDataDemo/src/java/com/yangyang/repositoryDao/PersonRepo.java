package com.yangyang.repositoryDao;

import com.yangyang.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//CrudRepository --> pagingAndSortingRepository
public interface PersonRepo extends
        JpaRepository<Person,Integer>,JpaSpecificationExecutor<Person>,PersonDao {

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

    // update and delete --> insert into
    @Modifying
    @Query("update Person p set p.email = :email where p.id=:id ")
    void updatePersonEmail(@Param("email") String email,@Param("id") Integer id);

}
