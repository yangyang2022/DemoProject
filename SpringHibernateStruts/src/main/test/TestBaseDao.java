import com.yangyang.dao.daoimpl.BaseDao;
import com.yangyang.model.User;
import com.yangyang.service.iservice.IGroupService;
import com.yangyang.service.iservice.IUserService;
import com.yangyang.utils.NameUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
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

    @Test
    public void testAddUsers() {
        Random rand = new Random();
        List<String> names = NameUtil.getNames(100);
        names.forEach(e->userService.add(new User(e,"123123","主席"+e,groupService.load(rand.nextInt(7)+1))));

    }

    @Test
    public void testDemoStr() {
        String sql = "select u from User u where u.username like ? ";
    }

}
