import com.yangyang.Iservice.IUserService;
import com.yangyang.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestHelloWorld {

    @Resource
    private IUserService userService;

    @Test
    public void testHelloWorld() {
        //User user = new User("admin","123123","主席",new Date());
        //userService.save(user);
        //List<String> names = NameUtil.getNames(500);
        //names.forEach(e->userService.save(new User(e,"123123","昵称",new Date())));

        //System.out.println(userService.count());

        //userService.delete(501);

        int pageNum = 3-1;
        int pageSize = 10;
        PageRequest page = new PageRequest(pageNum,pageSize);
        Page<User> users = userService.findAll(page);

        System.out.println("总记录数: "+users.getTotalElements());
        System.out.println("当前第几页: "+(users.getNumber()+1));
        System.out.println("总页数: "+users.getTotalPages());
        System.out.println("当前页面的list: "+users.getContent());
        System.out.println("当前页面的记录数: "+users.getNumberOfElements());
    }

    /**
     * 条件查询 分页 --> id>200 ==> 通用待条件查询
     */
    @Test
    public void testPage() {
        int pageNum = 3-1;
        int pageSize = 10;
        PageRequest page = new PageRequest(pageNum,pageSize);

        Page<User> users = userService.findAll(((root, query, cb) -> cb.gt(root.get("id"),200)),page);

        System.out.println("总记录数: "+users.getTotalElements());
        System.out.println("当前第几页: "+(users.getNumber()+1));
        System.out.println("总页数: "+users.getTotalPages());
        System.out.println("当前页面的list: "+users.getContent());
        System.out.println("当前页面的记录数: "+users.getNumberOfElements());
    }
    
}
