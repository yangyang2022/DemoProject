package ioc;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

public class TestAutoWire {
    private ApplicationContext ctx = null;

    @Before
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("beans.xml");
    }

    @Test
    public void testDataSource() {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource);
    }
    @Test
    public void testDemo1() {
        //Person p1 = ctx.getBean("yangyang1", Person.class);
        //System.out.println(p1);
        //Address ad = ctx.getBean("address2",Address.class);
        //Address ad2 = ctx.getBean("address2",Address.class);
        //System.out.println(ad);
        //System.out.println(ad == ad2);

    }
}
