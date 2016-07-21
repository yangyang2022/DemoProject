import com.yangyang.model.Address;
import com.yangyang.model.Person;
import com.yangyang.model.PersonRepositry;
import com.yangyang.model.PersonService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestDataSource {
    private ApplicationContext ctx = null;
    private PersonRepositry pr = null;
    private PersonService personService;

    {
        ctx = new ClassPathXmlApplicationContext("beans.xml");
        pr = ctx.getBean(PersonRepositry.class);
        personService = ctx.getBean(PersonService.class);
    }
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSourc = ctx.getBean("dataSource",DataSource.class);
        System.out.println(dataSourc.getConnection());
    }

    @Test
    public void testJPA() {
        Person person = pr.getByLastName("yangyang");
        System.out.println(person);
    }

    @Test
    public void testKeyWords() {
        List<Person> persons = pr.getByLastNameStartingWithAndIdLessThan("shen", 3);
        System.out.println(persons);
        persons =  pr.getByLastNameEndingWithAndIdLessThan("shen",3);
        System.out.println(persons);

        List<String> emails = Arrays.asList("11@qq.com","sads@qq.com","adsad@qq.com","ada@qq.com");

        persons = pr.getByEmailInOrBornLessThan(emails,new Date());
        System.out.println(persons.size());
        persons = pr.getByEmailInAndBornLessThan(emails,new Date());
        System.out.println(persons.size());

    }

    @Test
    public void testKeyWords2() {
        List<Person> persons = pr.getByAddress_IdGreaterThan(2);
        System.out.println(persons);

    }

    @Test
    public void testQuery1() {
        //Person person = pr.getMaxIdPerson();
        //System.out.println(person);

    }

    @Test
    public void testQuery2() {
        //Person p = pr.getPersonById(5,"yang");
        //System.out.println(p);
        
        //List<Person> ps = pr.getPersonByName("yang");
        //System.out.println(ps);
        //List<Person> ps = pr.getPersonByParam("yang");
        //System.out.println(ps);

        //List<Person> ps = pr.getPersonsLike("yang","ad");
        //System.out.println(ps.size());
        //System.out.println(ps);
        //
        //ps = pr.getPersonsLikeParam("yang","ad");
        //System.out.println(ps.size());

        long count = pr.getCountPerson();
        System.out.println("count: "+count);
    }

    @Test
    public void testUpdate() {
        //需要事物支持
        //pr.updatepersonEmail(1,"testMail.@qq.com");
        personService.updatePersonEmail(1,"testEmail@qq.com");
    }


    // ==================== TestCrudRepository  ====================== 
    @Test
    public void testCurdDemo1() {
        Address address = new Address("安徽","芜湖");
        List<Person> ps = new ArrayList<>();
        for (int i = 'a'; i <= 'z'; ++i) {
            Person p = new Person();
            p.setAddress(null);
            p.setLastName((char)i +""+(char)i);
            p.setBorn(new Date());
            p.setEmail((char)i +""+(char)i +"@yangyang.com");
            ps.add(p);
        }
        personService.savePersons(ps);
    }

    @Test
    public void testCRUDdemo2() {
        //long count = pr.count();
        //System.out.println("count: "+count);
        //Person p = pr.findOne(2);
        //System.out.println(p);

        // === save ===
        // === delete ==
        //personService.delete(2);
        //Iterable<Person> ps = pr.findAll();
        //for(Person p : ps){
        //    System.out.println(p.getLastName());
        //}
        //ps.forEach(e-> System.out.println(e.getLastName()));
    }

    @Test
    public void testPagingAndSorting() {
        //pageNo 从0 开始 ,不是从1 开始
        int page = 7-1; //这里是第7页
        int pageSize = 5;
        //排序相关 --> 封装了sort相关

        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"id");
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"email");

        Sort sort = new Sort(order1,order2);
        //Pageable 通常使用 PageRequest 封装
        Pageable pageAble = new PageRequest(page,pageSize,sort);
        Page<Person> ps = pr.findAll(pageAble);
        System.out.println("总记录数: "+ps.getTotalElements());
        System.out.println("当前第几页: "+(ps.getNumber()+1));
        System.out.println("总页数: "+ps.getTotalPages());
        System.out.println("当前页面的content: "+ps.getContent());
        System.out.println("当前页面的记录数: "+ps.getNumberOfElements());
    }

    @Test
    public void testJpaRepository() {
        Person person = new Person();
        person.setBorn(new Date());
        person.setLastName("testJpaRepository");
        person.setAddress(null);
        person.setEmail("merge@qq.com");
        person.setId(59);
        //like jpa merge
        //pr.saveAndFlush(person);
    }

    /**
     * 实现带查询条件的分页
     */
    @Test
    public void testSpecificationDemo() {
        int pageNo = 3 -1;
        int pageSize = 5;
        Pageable pager = new PageRequest(pageNo,pageSize);

        // 查询 id >5 的类
        //通常使用匿名内部类
        Specification<Person> spec = new Specification<Person>() {
            /**
             * @param root 代表查询的实体类
             * @param query: 可以从中得到root对象,即告知JPA CriteriaQuery要查询哪一个实体类,
             *             还可以添加查询条件 还可以结合entityManagerx对象得到最终查询的TypeQuery对象
             * @param cb : CriteriaBuilder 对象,创建Criteria 的工厂,当然可以从中获取到 Predicate 对象
             * @return Predicate 代表一个查询条件
             */
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.gt(root.get("id"),5) ;
                return predicate;
            }
        };

        //Page<Person> ps = pr.findAll(spec,pager);
        Page<Person> ps = pr.findAll(((root, query, cb) -> cb.gt(root.get("id"),20)),pager);

        System.out.println("总记录数: "+ps.getTotalElements());
        System.out.println("当前第几页: "+(ps.getNumber()+1));
        System.out.println("总页数: "+ps.getTotalPages());
        System.out.println("当前页面的content: "+ps.getContent());
        System.out.println("当前页面的记录数: "+ps.getNumberOfElements());

    }

    @Test
    public void testCustomTest() {
        pr.test();
    }
}
