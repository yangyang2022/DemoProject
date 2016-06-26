import com.yangyang.model.User;
import org.junit.Test;

public class TestNewInstance {
    private Object getInstance(Class clz){
        try {
            return clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Test
    public void testDemo1() {
        User u = (User) getInstance(User.class);
        System.out.println("u:"+u );
    }
}
