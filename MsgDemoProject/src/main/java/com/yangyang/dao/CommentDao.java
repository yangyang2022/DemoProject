package com.yangyang.model;

import com.yangyang.Utils.DBUtils;
import com.yangyang.mode.Comment;
import com.yangyang.mode.MsgException;
import com.yangyang.mode.Pager;
import com.yangyang.mode.SystemContext;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDao implements ICommentDao {
    private String table_name = "mt_comment";
    private IMessageDao messageDao;
    private IUserDao userDao;
    public CommentDao(){
        userDao = DaoFactory.getUserDao();
        messageDao = DaoFactory.getMsgDao();
    }
    @Override
    public void add(Comment comment, int userId, int msgId) {
        if(userDao.load(userId) == null) throw new MsgException("添加评论的用户不存在!");
        if(messageDao.load(msgId) == null) throw new MsgException("添加评论的留言不存在!");
        Connection con = null;
        PreparedStatement ps = null;
        // id content post_date user_id msg_id
        String sql = DBUtils.InserInto+table_name+" VALUES (NULL,?,?,?,?)";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setString(1,comment.getContent());
            ps.setTimestamp(2,new Timestamp(new Date().getTime()));
            ps.setInt(3,userId);
            ps.setInt(4,msgId);

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
        // id content post_date user_id msg_id
        String sql = DBUtils.DeleteFrom+table_name+" WHERE id=?";
        try {
            con = DBUtils.getConnetion();
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

    private Comment rs2Comment(ResultSet rs) throws SQLException {
        Comment com = new Comment();
        com.setId(rs.getInt("id"));
        com.setContent(rs.getString("content"));
        com.setUserId(rs.getInt("user_id"));
        com.setMsgId(rs.getInt("msg_id"));
        com.setPostDate(rs.getTimestamp("post_date"));
        return com;
    }
    @Override
    public Comment load(int id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Comment comment = null;
        // id content post_date user_id msg_id
        String sql = DBUtils.SelectAll+table_name+" WHERE id=?";
        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setInt(1,id);

            rs = ps.executeQuery();
            while (rs.next()){
                comment = rs2Comment(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.close(ps);
            DBUtils.close(con);
        }
        return comment;
    }

    @Override
    public Pager<Comment> list(int msgId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pager<Comment> pagers = new Pager<>();
        List<Comment> datas = new ArrayList<>();

        int pageOffset = SystemContext.getPageOffset();
        int pageSize = SystemContext.getPageSize();

        // =============== test pageOffset ======
        //int pageOffset = 0;
        //int pageSize= 15;

        pagers.setPageOffset(pageOffset);
        pagers.setPageSize(pageSize);

        // id content post_date user_id msg_id
        String sql = DBUtils.SelectAll+table_name+" WHERE msg_id=? "+DBUtils.OrderByPostDateAsc+DBUtils.Limit;
        String sqlCount = DBUtils.SelectCount+table_name+" where msg_id=?"+DBUtils.OrderByPostDateAsc;

        try {
            con = DBUtils.getConnetion();
            ps = con.prepareStatement(sql);
            ps.setInt(1,msgId);
            ps.setInt(2,pageOffset);
            ps.setInt(3,pageSize);

            rs = ps.executeQuery();
            while (rs.next()){
                datas.add(rs2Comment(rs));
            }

            ps = con.prepareStatement(sqlCount);
            ps.setInt(1,msgId);

            rs = ps.executeQuery();
            while (rs.next()){
                int totalRecord = rs.getInt(1);
                int totalPage = (totalRecord-1)/pageSize +1;
                pagers.setTotalPage(totalPage);
                pagers.setTotalRecord(totalRecord);
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
}
