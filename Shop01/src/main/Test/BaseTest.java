import com.yangyang.model.DaoFactory;

public class BaseTest {
    public BaseTest(){
        DaoFactory.injectDao(this);
    }
}
