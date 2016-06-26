package com.yangyang.web.Util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class MyBatisUtil {
    private static SqlSessionFactory factory;
    public static SqlSession createSession() throws IOException {
        factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        return factory.openSession();
    }
    public static void close(SqlSession session){
        if(session != null) session.close();
    }
}
