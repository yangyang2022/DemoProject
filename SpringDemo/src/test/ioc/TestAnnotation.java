package ioc;

import com.yangyang.annotation.TestObject;
import com.yangyang.annotation.controller.UserController;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAnnotation {
    private ApplicationContext ctx = null;

    @Before
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("beans-anno.xml");
    }

    @Test
    public void testDemo1() {
        TestObject testObject = ctx.getBean("testObject",TestObject.class);
        testObject.info();
    }

    @Test
    public void testDemo2() {
        UserController userController = ctx.getBean("userController",UserController.class);
        userController.save("yangyang");

    }
}
