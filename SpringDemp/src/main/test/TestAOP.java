import com.yangyang.dao.IUserDao;
import com.yangyang.model.User;
import com.yangyang.service.IUserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAOP {
    private BeanFactory factory = new ClassPathXmlApplicationContext("beans.xml");
    @Test
    public void testDemo1() {
        //IUserService service = factory.getBean("userService", UserService.class);
        IUserDao userDao = factory.getBean("userDao", IUserDao.class);
        User user = new User(1,"yangyang");
        userDao.add(user);
        userDao.delete(1);
        userDao.load(1);
    }

    @Test
    public void testDemo2() {
        IUserService userService = factory.getBean("userService",IUserService.class);
        User user = new User(1,"yangyang");
        userService.update(user);
    }
}
