import com.yangyang.model.UserDao;
import com.yangyang.model.UserService;
import org.junit.Test;

public class TestLog4j {
    @Test
    public void testDemo1() {
        String url = TestLog4j.class.getClassLoader().getResource("").getPath();
        url = url.replaceAll("target/test-classes/","src/main/resource/log");
        System.setProperty("URL_DIR",url);
        UserDao userDao = new UserDao();
        userDao.add();
        UserService us = new UserService();
        us.add();
    }
}
