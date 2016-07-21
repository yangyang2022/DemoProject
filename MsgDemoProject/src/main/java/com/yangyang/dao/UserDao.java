package com.yangyang.model;

import com.yangyang.Utils.DBUtils;
import com.yangyang.mode.MsgException;
import com.yangyang.mode.Pager;
import com.yangyang.mode.SystemContext;
import com.yangyang.mode.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao {

    //private String table_name = "t_msg";
    private String table_name = "mt_user";
    //private String table_name = "t_user";

    @Override
    public void add(User user) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        con = DBUtils.getConnetion();
        String sql = "select count(*) from "+table_name+" where username=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1,user.getUsername());
            rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getInt(1) > 0 ) throw new MsgException("添加的用户已经存在!");
            }
            sql = "insert into "+table_name+" VALUES (NULL ,?,?,?,?,?)";
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
        if(user.getUsername().equals("admin")) throw new MsgException("超级管理员 不能被删除!");

        String sql = "DELETE FROM "+table_name+" where id=?";
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
        String sql = "UPDATE "+table_name+" SET password=?,nickname=?,type=?,status=? where id=?";
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

        String sql = "SELECT * FROM "+table_name+" WHERE id=?";
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
    public Pager<User> list(String condition) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        Pager<User> pagers = new Pager<>();

        int pageSize = SystemContext.getPageSize();
        int pageOffset = SystemContext.getPageOffset();

        String sql = "SELECT * from "+table_name;
        String sqlCount = "select count(*) from "+table_name;

        try {
            con = DBUtils.getConnetion();
            if(condition != null ||!"".equals(condition.trim())){
                sql += " WHERE username like '%"+condition+"%' or nickname like '%"+condition+"%' ";
                sqlCount += " WHERE username like '%"+condition+"%' or nickname like '%"+condition+"%' ";
            }
            sql += " limit ?,?";
            ps = con.prepareStatement(sql);
            ps.setInt(1,pageOffset);
            ps.setInt(2,pageSize);

            //if(condition == null ||"".equals(condition.trim())){
            //    sql += " limit ?,? ";
            //    ps = con.prepareStatement(sql);
            //    ps.setInt(1,start);
            //    ps.setInt(2,pageSize);
            //}else {
            //    sql += " WHERE username like ? or nickname like ? ";
            //    ps = con.prepareStatement(sql);
            //    ps.setString(1,"%"+condition+"%");
            //    ps.setString(2,"%"+condition+"%");
            //}
            rs = ps.executeQuery();
            while (rs.next()){
                users.add(rs2User(rs));
            }
            ps = con.prepareStatement(sqlCount);
            rs = ps.executeQuery();
            int totalRecord = 0;
            while (rs.next()){
                totalRecord = rs.getInt(1);
            }
            int totalPage = (totalRecord-1)/pageSize+1;

            pagers.setPageOffset(pageOffset);
            pagers.setPageSize(pageSize);
            pagers.setTotalPage(totalPage);
            pagers.setTotalRecord(totalRecord);

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }

        pagers.setDatas(users);
        return pagers;
    }

    @Override
    public User login(String username, String password) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;

        String sql = "SELECT * from "+table_name+" where username=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setString(1,username);
            rs = ps.executeQuery();
            while (rs.next()){
                user = rs2User(rs);
            }
            if(user == null) throw new MsgException("用户名不存在!");
            if(!user.getPassword().equals(password)) throw new MsgException("密码错误!");
            if(user.getStatus() == 1) throw new MsgException("该用户处于停用状态,不能登录!");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return user;
    }
}
