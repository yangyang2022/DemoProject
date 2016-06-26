import com.yangyang.Utils.MyBatisUtil;
import com.yangyang.Utils.NameUtil;
import com.yangyang.dao.IUserDao;
import com.yangyang.dao.ShopDI;
import com.yangyang.model.Pager;
import com.yangyang.model.SystemContext;
import com.yangyang.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestUserDao {
    private IUserDao userDao;

    @ShopDI
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    //@Before
    //public void setup() {
    //    userDao = DaoFactory.getUserDao();
    //}
    @Test
    public void testUserAdd() {
        List<String> names = NameUtil.getNames(500);
        for(int i = 0;i<500;i++){
            User u = new User("user"+i,"123123",names.get(i),1);
            userDao.add(u);
        }
    }

    @Test
    public void testUpdate() {
        User u = userDao.loadByUsername("admin");
        u.setPassword("123123");
        userDao.update(u);
    }

    @Test
    public void testUserLoad() {
        //System.out.println(userDao.load(1));
        //System.out.println(userDao.loadByUsername("admin"));
        userDao.load(2).getAddresses().forEach(System.out::println);
    }

    @Test
    public void testUserDelete() {
        userDao.delete(3);
    }
    @Test
    public void testUserLogin() {
        userDao.login("admin","123123");
    }

    @Test
    public void testCon() {
        try {
            SqlSession session = MyBatisUtil.createSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testuserFind() {
        SystemContext.setPageSize(10);
        SystemContext.setPageOffset(0);
        SystemContext.setOrder("desc");
        SystemContext.setSort("id");

        Pager<User> users = userDao.find("张");
        System.out.println("count: "+users.getTotalRecord());
        System.out.println("size: "+users.getDatas().size());
        users.getDatas().forEach(System.out::println);

    }
}
