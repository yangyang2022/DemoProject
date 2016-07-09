package ioc;

import com.yangyang.factory.Car;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactory {
    private ApplicationContext ctx = null;

    @Before
    public void setup() {
        ctx = new ClassPathXmlApplicationContext("beans-factory.xml");
    }

    @Test
    public void testDemo1() {
        Car car = ctx.getBean("car1",Car.class);
        System.out.println(car);

        Car car2 = ctx.getBean("car2",Car.class);
        System.out.println(car2);

    }
}
