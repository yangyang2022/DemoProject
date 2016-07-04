import com.yangyang.springdata.Person;
import com.yangyang.springdata.PersonRepositry;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TestDataSource {
    private ApplicationContext ctx = null;
    private PersonRepositry pr = null;

    {
        ctx = new ClassPathXmlApplicationContext("beans.xml");
        pr = ctx.getBean(PersonRepositry.class);
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
}
