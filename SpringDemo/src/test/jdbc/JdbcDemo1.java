package jdbc;

import com.yangyang.jdbc.Department;
import com.yangyang.jdbc.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-jdbc.xml")
public class JdbcDemo1 {

    private ApplicationContext ctx = null;
    @Resource
    private JdbcTemplate jdbcTemplate;

    private Consumer print = System.out::println;
    private String sql = "";
    @Before
    public void testSetup() {
        ctx = new ClassPathXmlApplicationContext("beans-jdbc.xml");
        //jdbcTemplate = ctx.getBean("jdbcTemplate",JdbcTemplate.class);
    }

    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }

    @Test
    public void testupdate1() {
        /**
         *  update delete insert 都用update
         */
        String sql = "update t_user set username=? ,password=? where id=?";
        jdbcTemplate.update(sql,"jack","111222",1);
    }

    /**
     * 执行批量的更新 批量 insert/update/delete
     */
    @Test
    public void testBatchUpdate() {
        String sql = "INSERT INTO t_user" +
                " VALUES (NULL ,?,?,?)";

        List<Object[]> batchArgs = new ArrayList<>();
        batchArgs.add(new Object[]{"AA","AA洋洋",1});
        batchArgs.add(new Object[]{"BB","BB111222洋洋",2});
        batchArgs.add(new Object[]{"CC","CC111222洋洋",3});
        batchArgs.add(new Object[]{"DD","DD111222洋洋",1});

        jdbcTemplate.batchUpdate(sql,batchArgs);
    }

    /**
     * 从数据库获取一条记录
     */
    @Test
    public void testQueryForObject() {
        String sql = "select id,username,password,dept_id from t_user where id=?";
        //User user = jdbcTemplate.queryForObject(sql, new userMapper(),1);

        User user = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),1);
        System.out.println(user);

    }
    private class userMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            Department dep = new Department("测试");
            User user = new User(username,password,dep);
            user.setId(id);

            return user;
        }
    }

    /**
     * 获取集合 注意:不是使用queryForList,而是query()
     */
    @Test
    public void testQueryForList() {
        sql = "SELECT * from t_user WHERE  id > ?";
        List<User> users = jdbcTemplate.query(sql,new BeanPropertyRowMapper<>(User.class),3);
        users.forEach(print);
        System.out.println("=========================================");
        users = jdbcTemplate.query(sql,new Object[]{3},new userMapper());
        users.forEach(print);
    }
    /**
     * 获取单个列的值 或作统计查询
     * 
     */
    @Test
    public void testQueryforObject2() {
        sql = "SELECT COUNT(id) FROM t_user";
        long count = jdbcTemplate.queryForObject(sql,Long.class);
        System.out.println("count: "+count);
    }
}
