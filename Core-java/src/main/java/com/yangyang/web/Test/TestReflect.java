package com.yangyang.web.Test;

import com.yangyang.web.reflect.IUserDao;
import com.yangyang.web.reflect.UserADao;
import com.yangyang.web.reflect.UserBDao;
import com.yangyang.web.reflect.UserDemo;
import org.junit.Test;

import java.lang.reflect.Method;

public class TestReflect {
    @Test
    public void testCreate() {
        try {
            Class clz = Class.forName(UserDemo.class.getName());
            UserDemo u = (UserDemo)clz.newInstance();
            u.setId(1);
            u.setName("yangyang");

            System.out.println(u);
            u.show("yangyang");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUserDao1() {
        IUserDao userDao = new UserADao();
        userDao.add();
        
    }

    @Test
    public void testUserDao2() throws Exception {
        System.out.println(""+UserBDao.class.getName());
        IUserDao ud = (IUserDao) Class.forName(UserBDao.class.getName()).newInstance();
        ud.add();
    }

    @Test
    public void testUserMethod() throws Exception {
        Class clz = Class.forName(UserDemo.class.getName());
        UserDemo u = (UserDemo)clz.newInstance();
        u.setId(1);
        u.setName("yangyang");
        String m = "showw";

        //System.out.println("name "+clz.getDeclaredField("name"));

        Method method = clz.getMethod(m,String.class);
        String str = (String) method.invoke(u,"yangyang");
        System.out.println("str: "+str);
        String sm = "say";

        //调用static 方法需要使用 clz 来调用
        Method method2 = clz.getMethod(sm,String.class);
        method2.invoke(clz,"yangyang");
    }
}
