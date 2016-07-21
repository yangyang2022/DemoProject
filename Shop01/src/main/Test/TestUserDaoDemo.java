import com.yangyang.model.IUserDao;
import com.yangyang.model.ShopDI;
import org.junit.Test;

public class TestUserDaoDemo extends BaseTest{
    private IUserDao userDao;

    @ShopDI
    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Test
    public void testDemoLoad() {
        //IUserDao userDao = (IUserDao) DaoFactory.createFactor().getDao("userDao");
        System.out.println(userDao.load(1));
        //userDao.load(1);
    }
}
