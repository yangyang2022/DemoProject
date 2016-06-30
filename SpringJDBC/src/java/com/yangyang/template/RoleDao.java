package com.yangyang.template;

public class RoleDao {
    private myJdbcCon jdbcTemplate = new myJdbcCon();
    public void add(int id){
        jdbcTemplate.add(id);
    }
    public void delete(int id){
        jdbcTemplate.delete(id);
    }
}
