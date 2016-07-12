package com.yangyang.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

import static javafx.scene.input.KeyCode.T;

/**
 *  1: Repository 是一个空接口 ,是一个标记接口
 *  2: 若我们定义的接口继承了Repository,则会被IOC容器管理,会被识别为一个Repository Bean
 *  3: 也可以通过注解 RepositoryDefinition 来代替 extends Repository<Person,Integer>
 *  4: 支持级联查询,若当前类有符合条件的属性,则优先使用本类,不使用级联属性,使用_连接即可
 */

/**
 *   Repository 接口里面的方法 定义规范
 *  1: 查询方法以: find|read|get 开头,涉及查询条件时,需要用条件关键字链接
 *  条件属性 首字母 要大写
 */
//@RepositoryDefinition(domainClass = Person.class,idClass = Integer.class)
    //CrudRepository  crud 操作
    //PagingAndSortingRepository paging and sorting read-only 不用写在service
    //PagingAndSortingRepository
    //JpaRepository
    //JpaSpecificationExecutor 不属于上述体系,-->带查询条件的分页
    //Page<T> findAll(Specification<T> spec, Pageable pageable);
public interface PersonRepositry extends JpaRepository <Person,Integer>
        ,JpaSpecificationExecutor<Person>,PersonDao {

    Person getByLastName(String lastName);

    //where lastName like ?% AND id < ?
    List<Person> getByLastNameStartingWithAndIdLessThan(String lastName,int id);
    List<Person> getByLastNameEndingWithAndIdLessThan(String lastName,int id);

    //where email in (???) or borth <?
    List<Person> getByEmailInOrBornLessThan(List<String> emails, Date born);
    List<Person> getByEmailInAndBornLessThan(List<String> emails, Date born);

    //where a.id > ?
    List<Person> getByAddress_IdGreaterThan(int id);

    //查询ID 最大的ID 需要子查询 使用JPQL语句
    @Query("select p from Person p where p.id=(select max(p2.id) from Person p2)")
    Person getMaxIdPerson();

    //传递参数的方式一: 使用占位符 --> 有问题
    @Query("select p from Person p where p.id=?1 and p.lastName=?2")
    Person getPersonById(int id,String name);


    @Query(value = "select p from Person p where p.lastName = ?1")
    List<Person> getPersonByName(String name);
    //
    //命名参数的方式
    @Query("select p from Person p where p.lastName =:name")
     List<Person> getPersonByParam(@Param("name") String name);

    //spring data 允许在占位符上使用%%
    @Query("select p from Person p where p.lastName like %?1% or p.email like %?2% ")
    List<Person> getPersonsLike(String name,String email);

    //spring data 允许在占位符上使用%%
    @Query("select p from Person p where p.lastName like %:name% or p.email like %:email% ")
    List<Person> getPersonsLikeParam(@Param("name")String name,@Param("email") String email);


    //spring data 支持 本地sql查询
    @Query(value = "select count(id) from t_person" ,nativeQuery = true)
    long getCountPerson();

    // ===================== JPQL 不支持insert,可以update delete select ========================
    //可以完成update and delete ,但不支持insert into
    //注意: 需要事物,一般加在servie里面 并且必须加上 @Modifying 注解
    @Modifying
    @Query("update Person p set p.email =:email where id = :id")
    void updatepersonEmail(@Param("id") int id,@Param("email") String email);
}
