package ioc;

import com.yangyang.factorybean.Car;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanFactory {
    private ApplicationContext ctx = null;

    @Before
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("BeanFactory.xml");
    }
    @Test
    public void testDemo2() {
        Car car = ctx.getBean("car",Car.class);
        System.out.println(car);

    }
}
