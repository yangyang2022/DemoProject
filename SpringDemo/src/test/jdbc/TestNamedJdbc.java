package jdbc;

import com.yangyang.jdbc.Department;
import com.yangyang.jdbc.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans-jdbc.xml")
public class TestNamedJdbc {
    private String sql = "";

    @Resource
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /**
     * 可以为参数起名字
     * 优点: 多个参数
     */
    @Test
    public void testDemo1() {
        Map<String,String> params = new HashMap<>();
        sql = "INSERT INTO t_user VALUES (NULL ,:username,:password,:dep_id)";
        params.put("username","习近平");
        params.put("password","112233");
        params.put("dep_id","1");

        User user = new User("李克强","321321",new Department("ceshi"));
        SqlParameterSource names = new BeanPropertySqlParameterSource(user);

        namedParameterJdbcTemplate.update(sql, names);
    }
}
