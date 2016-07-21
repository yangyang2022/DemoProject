import com.yangyang.iservice.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@ContextConfiguration("/springcfg/beans.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TestDataSource {

    @Resource
    private IUserService userService;

    @Test
    public void testDemo() {
        System.out.println(userService.getByUsername("admin").getUsername());

    }
}
