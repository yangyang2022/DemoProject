package com.yangyang.model;

import com.yangyang.mode.Comment;
import com.yangyang.mode.Pager;

public interface ICommentDao {
    public void add(Comment comment, int userId, int msgId);
    public void delete(int id);
    public Comment load(int id);

    //commnet can't update

    public Pager<Comment> list(int msgId);
}
