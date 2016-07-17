import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class TestBanse {

    @Test
    public void testDemo() {
        SecurityManager manager = new IniSecurityManagerFactory("classpath:shiro.ini").getInstance();
        SecurityUtils.setSecurityManager(manager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("admin","admin");
        try {
            subject.login(token);
            PrincipalCollection ps = subject.getPrincipals();
            System.out.println(ps.asList().size());
            System.out.println(ps.getRealmNames());
            System.out.println(subject.getPrincipal().toString());

        } catch (UnknownAccountException e) {
            System.err.println("用户名不存在!");
        }catch (IncorrectCredentialsException e){
            System.err.println("密码不正确!");
        }
    }
}
