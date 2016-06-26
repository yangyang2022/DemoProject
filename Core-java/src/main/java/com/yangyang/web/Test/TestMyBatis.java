package com.yangyang.web.Test;

import com.yangyang.web.Mapper.UserMapper;
import com.yangyang.web.Util.MyBatisUtil;
import com.yangyang.web.model.User;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class TestMyBatis {
    @Test
    public void testMyBatisAdd() throws IOException {
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSession();
            User u = new User("yangyang","123","主席",1);
            session.getMapper(UserMapper.class).add(u);
            session.commit();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            MyBatisUtil.close(session);
        }
    }

    @Test
    public void testMyBatisLoad() {
        SqlSession session = null;
        try {
           session = MyBatisUtil.createSession();
            User u = session.getMapper(UserMapper.class).load(1);
            System.out.println(u);

        } catch (IOException e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            MyBatisUtil.close(session);
        }
    }

    @Test
    public void testUserList() {
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSession();
            List<User> list = session.getMapper(UserMapper.class).list();
            System.out.println(list.size());

        } catch (IOException e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            MyBatisUtil.close(session);
        }
    }

    @Test
    public void testList2() {
        SqlSession session = null;
        try {
            session = MyBatisUtil.createSession();
            List<User> us = session.selectList(User.class.getName()+".list",null);
            System.out.println(us.size());

        } catch (IOException e) {
            session.rollback();
            e.printStackTrace();
        }finally {
            MyBatisUtil.close(session);
        }
    }
}
