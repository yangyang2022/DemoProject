package com.yangyang.web.Mapper;

import com.yangyang.web.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    /**
     * 基于annotation 的需要引入class 的 mapper 文件
     * @param user
     */
    @Insert("insert into t_user values(null,#{username},#{password},#{nickname},#{type})")
    void add(User user);

    void update(User user);

    @Delete("delete from t_user where id=#{id}")
    void delete(int id);

    @Select("select * from t_user where id= #{id}")
    User load(int id);
    @Select("select * from t_user")
    List<User> list();
}
