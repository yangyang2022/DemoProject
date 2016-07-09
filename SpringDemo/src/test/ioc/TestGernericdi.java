package ioc;

import com.yangyang.annotation.gernericdi.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestGernericdi {
    private ApplicationContext ctx = null;

    @Before
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("genericdi.xml");
    }

    @Test
    public void testDemo1() {
        /**
         *  泛型依赖注入( spring4.0之后加入 )
         */
        UserService userService = ctx.getBean("userService",UserService.class);
        userService.add();

    }
}
