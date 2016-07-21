package com.yangyang.model;

import com.yangyang.mode.Message;
import com.yangyang.mode.Pager;

public interface IMessageDao {
    public void add(Message msg, int userId);
    public void update(Message msg);
    public void delete(int id);
    public Message load(int id);
    public Pager<Message> list();
    public int getCommentCount(int msg_id);
}
