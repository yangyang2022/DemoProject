import com.yangyang.model.User;
import com.yangyang.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    private BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void testDemo1() {
        UserService userService = factory.getBean("userService",UserService.class);
        User user = new User(1,"悟空");
        userService.add(user);
        //userService.getNames().forEach(System.out::println);
    }

    @Test
    public void testDemo2() {
        User u = factory.getBean("user",User.class);
        System.out.println(u);
    }
}
