package ioc;

import com.yangyang.model.HelloWorld;
import com.yangyang.model.Person;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestHelloWorld {
    private ApplicationContext ctx = null;

    @Test
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("beans.xml");
    }
    @Test
    public void testDemo1() {
        HelloWorld hello = new HelloWorld();
        hello.setName("yangyang");
        hello.hello();
    }

    @Test
    public void testDemo2() {
        // 1: 创建IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        // 2: 从ioc容器中获取bean
        //HelloWorld hello = ctx.getBean("helloWorld",HelloWorld.class);
        //hello.hello();
        //
        //HelloWorld helloWorld2 = ctx.getBean(HelloWorld.class);
        //helloWorld2.hello();

        //Car car = ctx.getBean("car",Car.class);
        //System.out.println(car);

        //Person person = ctx.getBean("person",Person.class);
        //System.out.println(person.getName()+" --> "+person.getCar());
        //Person person2 = ctx.getBean("person2",Person.class);
        //System.out.println(person2.getName()+" --> "+person2.getCar());

        //Person person = ctx.getBean("person3",Person.class);
        //System.out.println(person.getName()+" --> "+person.getCar());

        //Person yangyang = ctx.getBean("yangyang",Person.class);
        //System.out.println(yangyang);

        //DataSource dataSource = ctx.getBean("dataSource",DataSource.class);
        //System.out.println(dataSource);

        Person person = ctx.getBean("Tom",Person.class);
        System.out.println(person);
        Person jerry = ctx.getBean("jerry",Person.class);
        System.out.println(jerry);

    }
}
