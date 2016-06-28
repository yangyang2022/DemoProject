import com.yangyang.model.User;
import com.yangyang.service.IUserService;
import com.yangyang.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAOP {
    private BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void testDemo1() {
        IUserService service = factory.getBean("userService", UserService.class);
        User user = new User(1,"yangyang");
        service.add(user);
        service.update(user);
        service.load(1);
        service.delete(1);
    }
}
