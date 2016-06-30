package com.yangyang.dao;

import com.yangyang.model.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Repository("groupJdbcDao")
public class GroupDao implements IGroupDao{
    private JdbcTemplate jdbcTemplate;
    @Resource
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void add(Group group) {

        jdbcTemplate.update("INSERT INTO t_group VALUES (NULL ,?)",group.getName());
        int get_last_insertID = jdbcTemplate.queryForObject(
                "SELECT LAST_INSERT_ID()", null, Integer.class);
        group.setId(get_last_insertID);
    }
}
