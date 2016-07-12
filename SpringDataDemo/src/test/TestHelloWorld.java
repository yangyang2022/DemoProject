import com.yangyang.model.Person;
import com.yangyang.service.PersonService;
import com.yangyang.utils.NameUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestHelloWorld {

    private Consumer print  = System.out::println;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersonService personService;

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

        System.out.println(personService.getTotleCount());

    }
}
