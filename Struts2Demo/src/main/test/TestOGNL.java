import com.yangyang.model.Department;
import com.yangyang.model.Role;
import com.yangyang.model.User;
import ognl.Ognl;
import ognl.OgnlException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestOGNL {
    @Test
    public void testOGNL1() {
        Department dep = new Department("sanguo");
        User user = new User(1,"ts","唐僧");
        user.setDep(dep);
        try {
            System.out.println(Ognl.getValue("id",user));
            System.out.println(Ognl.getValue("dep.name",user));
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testO2() {
        Map<String ,Object> ctx = new HashMap<>();
        Department dep = new Department("sanguo");
        User user = new User(1,"ts","唐僧");
        user.setDep(dep);

        Role role = new Role(1,"admin");
        ctx.put("user",user);
        ctx.put("role",role);

        try {
            //expre -- ctx -- root
            System.out.println(Ognl.getValue("username",ctx,user));
            System.out.println(Ognl.getValue("#role.name",ctx,user));

            System.out.println(Ognl.getValue("role.name",ctx,ctx));

            //ognl就是一个大的 context key -- root ,所以可以通过#root.xxx来获取
            System.out.println(Ognl.getValue("#root.username",ctx,user));
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testO3() {
        List<User> us = new ArrayList<>();
        us.add(new User(1,"ts","tangseng"));
        us.add(new User(2,"bj","bajie"));
        us.add(new User(3,"wk","wukong"));
        us.add(new User(4,"ss","shaseng"));

        try {
            //取 list 中的元素 --> #root[0].xxx
            System.out.println(Ognl.getValue("#root[0].password",us));
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testO4() {
        // 获取方法 --> 可以完成方法的调用
        List<User> us = new ArrayList<>();
        us.add(new User(1,"ts","tangseng"));
        us.add(new User(2,"bj","bajie"));
        us.add(new User(3,"wk","wukong"));
        us.add(new User(4,"ss","shaseng"));

        try {
            //取 list 中的元素 --> #root[0].xxx
            System.out.println(Ognl.getValue("#root[0].sum(1,3)",us));
            System.out.println(Ognl.getValue("#root[0].hello('yangyang')",us));

            //直接调用 方法
            User u = new User();
            System.out.println(Ognl.getValue("hello('yangyang')",u));
            System.out.println(Ognl.getValue("get(0).hello('yangyang')",us));
            System.out.println(Ognl.getValue("size()",us));
        } catch (OgnlException e) {
            e.printStackTrace();
        }
    }
}
