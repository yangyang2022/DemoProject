import com.yangyang.dao.DaoFactory;

public class BaseTest {
    public BaseTest(){
        DaoFactory.injectDao(this);
    }
}
