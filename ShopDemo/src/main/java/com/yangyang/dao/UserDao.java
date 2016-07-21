package com.yangyang.model;

import com.yangyang.Utils.DBUtils;
import com.yangyang.mode.ShopException;
import com.yangyang.mode.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    @Override
    public void add(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = DBUtils.getConnetion();
        String sql = "select count(*) from t_user where username=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getInt(1) > 0 ) throw new ShopException("添加的用户已经存在!");
            }
            sql = "insert into t_user VALUES (NULL ,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getNickname());
            ps.setInt(4,user.getType());
            ps.setInt(5,user.getStatus());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        con = DBUtils.getConnetion();

        User user = load(id);
        if(user.getUsername().equals("admin")) throw new ShopException("超级管理员 不能被删除!");

        String sql = "DELETE FROM t_user where id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }

    @Override
    public void update(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        String sql = "UPDATE t_user SET password=?,nickname=?,type=?,status=? where id=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getPassword());
            ps.setString(2,user.getNickname());
            ps.setInt(3,user.getType());
            ps.setInt(4,user.getStatus());
            ps.setInt(5,user.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }

    @Override
    public User load(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        String sql = "SELECT * FROM t_user WHERE id=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while (rs.next()){
                user = rs2User(rs);
                //user =  new User();
                //user.setId(id);
                //user.setUsername(rs.getString("username"));
                //user.setPassword(rs.getString("password"));
                //user.setNickname(rs.getString("nickname"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return user;
    }

    private User rs2User(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setNickname(rs.getString("nickname"));
        user.setStatus(rs.getInt("status"));
        user.setType(rs.getInt("type"));
        return user;
    }
    @Override
    public List<User> list() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        String sql = "SELECT * from t_user";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                users.add(rs2User(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return users;
    }

    @Override
    public User login(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        String sql = "SELECT * from t_user where username=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while (rs.next()){
                user = rs2User(rs);
            }
            if(user == null) throw new ShopException("用户名不存在!");
            if(!user.getPassword().equals(password)) throw new ShopException("密码错误!");
            if(user.getStatus() == 1) throw new ShopException("该用户处于停用状态,不能登录!");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return user;
    }
}
