package ioc;

import com.yangyang.cycle.CarDemo;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpel {
    private ClassPathXmlApplicationContext ctx = null;

    @Before
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("beans-spel.xml");
    }

    @Test
    public void testDemo1() {
        //Address address = ctx.getBean("address", Address.class);
        //System.out.println(address);
        //Car car = ctx.getBean("car",Car.class);
        //System.out.println(car);

        //Person person1 = ctx.getBean("person", Person.class);
        //System.out.println(person1);

        CarDemo car = ctx.getBean("carDemo" ,CarDemo.class);
        System.out.println(car);
        ctx.close();

    }
}
