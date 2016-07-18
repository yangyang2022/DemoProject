import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

import java.util.Arrays;

import static com.yangyang.utils.ShiroUtile.login;

public class TestBanse {


    @Test
    public void testDemo() {
        Subject subject = login("kh","123");

        PrincipalCollection ps = subject.getPrincipals();
        System.out.println(ps.asList());
        System.out.println(ps.getRealmNames());
        System.out.println(subject.getPrincipal());

    }

    @Test
    public void testRole() {
        Subject subject = login("kh", "123");
        System.out.println(subject.hasRole("r3"));
        System.out.println(subject.hasAllRoles(Arrays.asList("r1","r2","r3")));
        System.out.println((subject.hasRoles(Arrays.asList("r1","r2","r3")))[0]);

        subject.checkRole("r3");

    }

    @Test
    public void testRole2() {
        Subject subject = login("kh", "123");
        System.out.println(subject.isPermitted("user:query"));
        System.out.println(subject.isPermitted("topic:xada"));
        System.out.println(subject.isPermitted("dep:delete"));
        System.out.println(subject.isPermitted("classroom:delete"));
    }

    @Test
    public void testRole3() {
        Subject subject = login("lisi", "lisi");
        System.out.println(subject.isPermitted("admin:dep:delete"));
        System.out.println(subject.isPermitted("cao:view"));
        System.out.println(subject.isPermitted("test:user:view"));
    }

    @Test
    public void testMyPermission() {
        Subject subject = login("kh", "123");
        System.out.println(subject.isPermitted("+topic+ads"));
    }

    @Test
    public void test() {
        int num = 123;
        System.out.println((new StringBuilder(num+"")).reverse());
    }
}
