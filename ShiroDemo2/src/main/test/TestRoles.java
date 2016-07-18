import com.yangyang.utils.ShiroUtile;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestRoles {
    @Test
    public void testRole1() {
        
        String filename = "shiro_role.ini";
        
        Subject subject = ShiroUtile.login("kh","123",filename);
        System.out.println(subject.getPrincipal());
        System.out.println(subject.hasRole("role3"));
    }
}
