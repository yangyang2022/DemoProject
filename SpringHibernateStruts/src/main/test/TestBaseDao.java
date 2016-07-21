import com.yangyang.service.iservice.IGroupService;
import com.yangyang.service.iservice.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-hibernate.xml")
public class TestBaseDao {

    private Consumer print = System.out::println;

    @Resource
    private IUserService userService;

    @Resource
    private IGroupService groupService;

    @Test
    public void testUser() {

        //userDao.listAll().forEach(print);
        //groupDao.listAll().forEach(print);
        //System.out.println(userDao.load(1));
        //Group g = groupDao.load(1);
        //User u = new User("yangyang2","yangyang","123123",g);
        ////userDao.add(u,g.getId());
        //userDao.add(u);
    }

    @Test
    public void testUserService() {
        //groupService.delete(1);
        //groupService.listAll().forEach(print);
        //groupService.delete(3);
        groupService.delete(4);

    }

}
