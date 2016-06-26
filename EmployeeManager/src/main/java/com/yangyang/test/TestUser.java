package com.yangyang.test;

import com.yangyang.dao.DepDao;
import com.yangyang.dao.EmpDao;
import com.yangyang.dao.UserDao;
import com.yangyang.model.Dep;
import com.yangyang.model.Emp;
import com.yangyang.model.User;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TestUser {
    
    private UserDao userDao;
    private DepDao depDao;
    private EmpDao empDao;
    @Before
    public void setup(){
        userDao = new UserDao();
        depDao = new DepDao();
        empDao = new EmpDao();
    }
    @Test
    public void testUserAdd() {
        User u = new User("admin1","admin","admin");
        userDao.add(u);
        System.out.println("ok ... ");
    }
    @Test
    public void testUserLoad(){
        User user = userDao.load("yangyangx");
        System.out.println(user);
    }

    @Test
    public void testUserDelete() {
        userDao.delete("yangyang");
    }

    @Test
    public void testUserUpdata() {
        User user = new User("adminx","xxx","xxx");
        userDao.update(user);
    }

    @Test
    public void testUserList() {
        List<User> users = userDao.list();
        users.forEach(System.out::println);
    }

    @Test
    public void testDepAdd() {
        Dep dep = new Dep(1,"学生处");
        depDao.add(dep);
    }

    @Test
    public void testDepAdd2() {
        Dep dep = new Dep("学生处");
        depDao.add(dep);
    }
    @Test
    public void testDepLoadById() {
        Dep dep = depDao.load(3);
        System.out.println(dep);
    }
    @Test
    public void testDepDelete(){
        depDao.delete(2);
    }

    @Test
    public void testDepUpdate() {
        Dep dep = new Dep(31,"dangzhongyang");
        depDao.update(dep);
    }

    @Test
    public void testDepList() {
        List<Dep> deps = depDao.list();
        deps.forEach(System.out::println);
    }

    // ==================== TestEmp ======================
    @Test
    public void testEmpAdd() {
        for (int i = 0; i < 10; ++i) {
            Emp emp1 = new Emp("yangyang"+i,"男",123.123);
            empDao.add(emp1,1);
        }
    }

    @Test
    public void testEmpDelete() {
        empDao.delete(1);//id
    }

    @Test
    public void testEmpUpdate() {
        Emp emp = new Emp();
        emp.setId(2);emp.setUsername("yangyang2");
        emp.setSex("女");emp.setSalary(10000.0);emp.setDepId(2);
        empDao.update(emp,1);
    }

    @Test
    public void testEmpLoad() {
        System.out.println(empDao.load(40));
    }
    @Test
    public void testEmpList() {
        List<Emp> emps = empDao.list();
        emps.forEach(System.out::println);
    }

    // ==================== TestEmps ======================
    @Test
    public void testEmpCondition() {
        List<Emp> emps = empDao.list(1,"ssada");
        emps.forEach(System.out::println);
    }

    @Test
    public void testDemoAdd() {
        depDao.add(new Dep("hehe"));
    }
}
