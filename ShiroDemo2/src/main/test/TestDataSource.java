import com.yangyang.IService.IUserService;
import com.yangyang.realm.MyRealm;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestDataSource {
    @Resource
    private IUserService userService;

    @Resource
    private MyRealm myRealm;

    @Test
    public void testDataSource() {
        System.out.println("hello world");
    }

    @Test
    public void testLoad() {
        //Role role = new Role();
        //role.setId(1);
        //role.setRoleName("admin");
        //User user = new User();
        //user.setUsername("洋洋");
        //user.setPassword("123123");
        //user.setRole(role);
        //
        //userService.save(user);

        //User user = userService.getByUsername("yangyang");
        //System.out.println(user.getUsername()+" : "+user.getPassword());

        //Set<String> roles = userService.getRoles("yangyang");
        //System.out.println(roles);

        //Set<String> perms = userService.getPerms("yangyang");
        //System.out.println(perms);

        System.out.println(myRealm.getName());
        //System.out.println(userService.getByUsername("admin").getUsername());
    }

    @Test
    public void testMD5() {
        String password = new Md5Hash("123123", "admin",100).toString();
        String password2 = new Md5Hash("123123", "admin",100).toString();
        System.out.println(password);
        System.out.println(password2);
        System.out.println(password.equals(password2));
    }
}
