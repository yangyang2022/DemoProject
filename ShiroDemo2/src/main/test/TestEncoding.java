import com.yangyang.utils.ShiroUtile;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestEncoding {

    @Test
    public void testDemo1() {
        Subject subject = ShiroUtile.login("kh","123");
        System.out.println(subject.getPrincipal());
    }

    @Test
    public void testPasswordService() {

        DefaultPasswordService ps = new DefaultPasswordService();
        //这种方式不灵活,不推荐使用
        String str1 = ps.encryptPassword("123");
        String str2 = ps.encryptPassword("123");
        System.out.println(str1.equals(str2));
        System.out.println(ps.passwordsMatch(str1,"123"));
    }

    @Test
    public void testPass2() {
        System.out.println(new Md5Hash("123123"));

    }
}
