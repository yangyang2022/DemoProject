import com.yangyang.dao.IGroupDao;
import com.yangyang.dao.IUserDao;
import com.yangyang.model.Group;
import com.yangyang.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

//让spring运行在junit环境中
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestJDBC {
    @Resource
    private IUserDao userjdbcDao;

    @Resource
    private IGroupDao groupJdbcDao;

    @Test
    public void testUserAdd() {
        Group g = new Group("管理员");
        groupJdbcDao.add(g);

        User user = new User("小莫","123123","福主席");
        userjdbcDao.add(user,g.getId());
    }

    @Test
    public void testGroupAdd() {
        Group g = new Group("计生办1");
        groupJdbcDao.add(g);
        System.out.println(g.getId()+" : "+g.getName());
    }
    @Test
    public void testUserUpdate() {
        User user = new User("洋洋","123123","主席");
        user.setId(1);
        userjdbcDao.update(user);
    }

    @Test
    public void testUserDelete() {
        userjdbcDao.delete(2);
    }

    @Test
    public void testUserLoad() {
        User u = userjdbcDao.load(5);
        System.out.println(u.getUsername()+" : "+u.getGroup().getName());
    }

    @Test
    public void testUserList() {
        String sql = "select t1.id uid,t1.*,t2.* from t_user t1 left join t_group t2 on(t1.gid = t2.id)";

        userjdbcDao.list(sql,null).forEach(e-> System.out.println(e.getUsername()+" : "+e.getGroup().getName()));

    }
}
