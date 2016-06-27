package com.yangyang.dao;

import com.yangyang.model.Message;

import java.util.Date;

public class MsgDao {
    public Message load(){
        Message msg = new Message(99,"title","content");
        msg.setCreateDate(new Date());
        return msg;
    }
}
