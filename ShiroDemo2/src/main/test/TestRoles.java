import com.yangyang.utils.ShiroUtile;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.function.Consumer;

public class TestRoles {

    private Consumer print = System.out::println;

    @Test
    public void testRole1() {
        
        String filename = "shiro_role.ini";
        
        Subject subject = ShiroUtile.login("kh","123",filename);
        System.out.println(subject.getPrincipal());
        System.out.println(subject.hasRole("role3"));
    }

    @Test
    public void testPermission() {
        String filename = "shiro_permission.ini";
        Subject subject = ShiroUtile.login("kh","123",filename);
        System.out.println(subject.isPermitted("user:query"));
    }
}
