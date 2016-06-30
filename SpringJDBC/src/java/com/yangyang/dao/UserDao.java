package com.yangyang.dao;

import com.yangyang.model.Group;
import com.yangyang.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("userjdbcDao")
public class UserDao implements IUserDao{
    private JdbcTemplate jdbcTemplate;
    private IGroupDao groupDao;

    @Resource(name = "groupJdbcDao")
    public void setGroupDao(IGroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(User user,int gid) {
        jdbcTemplate.update("INSERT INTO t_user VALUES (NULL ,?,?,?,?)",
                user.getUsername(),user.getPassword(),user.getNickname(),gid);
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("UPDATE t_user SET username=?,password=?,nickname=? WHERE id=?"
        ,user.getUsername(),user.getPassword(),user.getNickname(),user.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM t_user WHERE id=?",id);
    }

    @Override
    public User load(int id) {
        String sql = "select t1.id uid,t1.*,t2.* from t_user t1 left join t_group t2 on(t1.gid = t2.id) where t1.id=?";
        User user = (User) jdbcTemplate.queryForObject(sql, new Object[]{id},new UserMapper());
        return user;
    }

    private class UserMapper implements RowMapper<User>{
        @Override
        public User mapRow(ResultSet rs, int i) throws SQLException {
            Group g = new Group(rs.getInt("gid"),rs.getString("name"));
            User user = new User(rs.getInt("uid"),rs.getString("username"),rs.getString("password"),rs.getString("nickname"));
            user.setGroup(g);
            return user;
        }
    }
    @Override
    public List<User> list(String sql,Object[] args) {

        String sqlCount = "select count(*) from t_user";
        int count = jdbcTemplate.queryForObject(sqlCount,null,Integer.class);
        System.out.println("count: "+count);

        String nsql = "select username FROM t_user";
        List<String> ns = jdbcTemplate.queryForList(nsql,String.class);
        for(String s:ns){
            System.out.println("---> "+s);
        }

        String tsql = " select username,nickname from t_user";

        return jdbcTemplate.query(sql,args,new UserMapper());
    }
}
