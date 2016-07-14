import com.yangyang.Iservice.IPersonService;
import com.yangyang.model.Person;
import com.yangyang.utils.NameUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestHelloWorld {

    private Consumer print  = System.out::println;

    @Autowired
    private DataSource dataSource;

    @Resource
    private IPersonService personService;

    @Test
    public void testDataSource() throws SQLException {
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testPersonSave() {
        //Person person = new Person("洋洋","yangyang@gmail.com",new Date());
        //personService.save(person);

        //System.out.println(personService.count());
        //Iterable<Person> ps = personService.findAll();
        //ps.forEach(e-> System.out.println(e));
        //personService.delete(1);
        //personService.getByLastName("洋洋");

        //personService.getAll().forEach(print);

        List<String> names = NameUtil.getNames(500);
        names.forEach(e->personService.save(new Person(e,"yangyang@gmail.com",new Date())));

    }

    @Test
    public void testGetTable() {
        
    }
    @Test
    public void testDemo2() {
        //personService.getByLastnameStartingWith("沈").forEach(print);
        //System.out.println(personService.getMaxIdPerson());
        //personService.getPersonByUsername2("洋洋","yangyang@gmail.com").forEach(print);

        //personService.getPersonlike2("沈","yangyangx@gmail.com").forEach(print);
        //System.out.println(personService.count());

        //System.out.println(personService.getTotleCount());
        //Person person = new Person("洋洋123","yangyang@gmail.com",new Date());
        //personService.save(person);

        //personService.delete(2);
        //personService.updatePersonEmail("xxx@gmail.com",3);

        List<Person> ps = Arrays.asList(new Person("习近平1","yangyang@gmail.com",new Date()),
                new Person("习近平2","yangyang@gmail.com",new Date()) );

        personService.save(ps);


    }

    @Test
    public void testPaging() {
        /**
         * PageRequest 封装了分页信息
         */
        int pageNum = 3 -1 ; //注意: 这里从0开始,
        int pageSize = 5;   //这里是第三页 5条记录

        //排序相关 Sort 封装了排序的信息
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC,"id");//id 降序
        Sort.Order order2 = new Sort.Order(Sort.Direction.ASC,"email");//email 升序

        Sort sort = new Sort(order1,order2);

        PageRequest pageRequest = new PageRequest(pageNum,pageSize,sort);
        Page<Person> persons = personService.findAll(pageRequest);
        
        System.out.println("总记录数: "+persons.getTotalElements());
        System.out.println("当前第几页: "+(persons.getNumber()+1));
        System.out.println("总页数: "+persons.getTotalPages());
        System.out.println("当前页面的list: "+persons.getContent());
        System.out.println("当前页面的记录数: "+persons.getNumberOfElements());
    }

    /**
     * 带查询条件的分页 id > 5
     * Page<Person> findAll(Specification<Person> spec, Pageable pageable);
     * Specification 封装了查询条件
     * Pageable: 封装了分页信息
     */
    @Test
    public void testSort() {

        int pageNum = 1-1;
        int pageSize = 5;
        PageRequest page = new PageRequest(pageNum,pageSize);
        Specification<Person> spec = null;//查询条件,通常使用匿名内部类
        spec = new Specification<Person>() {
            //root: 查询的实体类
            //query: 可以从中得到root对象
            //cb:
            @Override
            public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return null;
            }
        };
        //root 进行导航作用,id是属性,gt是 >
        Page<Person> persons = personService.findAll(((root, query, cb) -> cb.gt(root.get("id"),5)),page);

        System.out.println("总记录数: "+persons.getTotalElements());
        System.out.println("当前第几页: "+(persons.getNumber()+1));
        System.out.println("总页数: "+persons.getTotalPages());
        System.out.println("当前页面的list: "+persons.getContent());
        System.out.println("当前页面的记录数: "+persons.getNumberOfElements());

    }

    @Test
    public void testTest() {
        personService.testPerson();
    }
}
