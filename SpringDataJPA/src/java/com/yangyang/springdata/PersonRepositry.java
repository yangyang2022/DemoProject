package com.yangyang.springdata;


import org.springframework.data.repository.RepositoryDefinition;

import java.util.Date;
import java.util.List;

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
@RepositoryDefinition(domainClass = Person.class,idClass = Integer.class)
public interface PersonRepositry  {
    Person getByLastName(String lastName);

    //where lastName like ?% AND id < ?
    List<Person> getByLastNameStartingWithAndIdLessThan(String lastName,int id);
    List<Person> getByLastNameEndingWithAndIdLessThan(String lastName,int id);

    //where email in (???) or borth <?
    List<Person> getByEmailInOrBornLessThan(List<String> emails, Date born);
    List<Person> getByEmailInAndBornLessThan(List<String> emails, Date born);

    //where a.id > ?
    List<Person> getByAddress_IdGreaterThan(int id);

}
