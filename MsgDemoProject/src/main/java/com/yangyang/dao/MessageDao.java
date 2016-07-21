package com.yangyang.model;

import com.yangyang.Utils.DBUtils;
import com.yangyang.mode.Message;
import com.yangyang.mode.MsgException;
import com.yangyang.mode.Pager;
import com.yangyang.mode.SystemContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MessageDao implements IMessageDao {
    private IUserDao userDao;
    private String table_name = "mt_msg ";
    private String comment_tableName = "mt_comment";
    public MessageDao(){
        userDao = DaoFactory.getUserDao();
    }

    @Override
    public void add(Message msg, int userId) {
        if(userDao.load(userId) == null){
            throw new MsgException("添加的用户不存在!");
        }
        Connection con = null;
        PreparedStatement ps = null;
        //title content post_date user_id
        String sql = "INSERT INTO "+table_name+" VALUES (NULL ,?,?,?,?)";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setString(1,msg.getTitle());
            ps.setString(2,msg.getContent());
            ps.setTimestamp(3,new Timestamp(new Date().getTime()));
            ps.setInt(4,msg.getUserId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }

    @Override
    public void update(Message msg) {
        Connection con = null;
        PreparedStatement ps = null;
        //title content post_date user_id
        String sql = "UPDATE "+table_name+" SET title=?,content=? WHERE id=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setString(1,msg.getTitle());
            ps.setString(2,msg.getContent());
            ps.setInt(3,msg.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
    }

    @Override
    public void delete(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        //删除评论
        String sql = "DELETE FROM "+comment_tableName+" WHERE msg_id=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            ps.executeUpdate();

            sql = "DELETE FROM "+table_name+" WHERE id=?";
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

    private Message rs2Message(ResultSet rs) throws SQLException {
        Message msg = new Message();
        msg.setTitle(rs.getString("title"));
        msg.setContent(rs.getString("content"));
        msg.setId(rs.getInt("id"));
        msg.setPostTime(new Date(rs.getTimestamp("post_date").getTime()));
        msg.setUserId(rs.getInt("user_id"));
        return msg;
    }
    @Override
    public Message load(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Message msg = null;

        String sql = "SELECT * FROM "+table_name+" WHERE id=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();

            while (rs.next()){
                msg = rs2Message(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return msg;
    }

    @Override
    public Pager<Message> list() {
        Pager<Message> pagers = new Pager<>();
        List<Message> datas = new ArrayList<>();

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int pageOffset = SystemContext.getPageOffset();
        int pageSize = SystemContext.getPageSize();

        // =============== test pageOffset ======
        //int pageOffset = 0;
        //int pageSize= 15;

        pagers.setPageOffset(pageOffset);
        pagers.setPageSize(pageSize);

        String sql = "SELECT * FROM "+table_name +" ORDER BY post_date DESC limit ?,? ";
        String sqlCount = "SELECT count(*) FROM "+table_name +" ORDER BY post_date DESC limit ? ,? ";
        try {
            con = DBUtils.getConnetion();

            ps = con.prepareStatement(sql);
            ps.setInt(1,pageOffset);
            ps.setInt(2,pageSize);
            rs = ps.executeQuery();
            while (rs.next()){
                datas.add(rs2Message(rs));
            }
            ps = con.prepareStatement(sqlCount);
            ps.setInt(1,pageOffset);
            ps.setInt(2,pageSize);
            rs = ps.executeQuery();
            while (rs.next()){
                int totalRecord = rs.getInt(1);
                int totalPage = (totalRecord-1)/pageSize+1;
                pagers.setTotalRecord(totalRecord);
                pagers.setTotalPage(totalPage);
                pagers.setDatas(datas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return pagers;
    }

    @Override
    public int getCommentCount(int msg_id) {
        int count = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT count(*) FROM mt_comment WHERE msg_id=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setInt(1,msg_id);
            rs = ps.executeQuery();
            while (rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(rs);
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return count;
    }
}
