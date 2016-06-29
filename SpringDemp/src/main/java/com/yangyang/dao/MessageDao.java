package com.yangyang.dao;

import com.yangyang.model.Message;
import com.yangyang.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao implements IMessageDao{
    @Override
    public void add(Message msg) {
        System.out.println("add a "+msg);
    }

    @Override
    public void update(Message msg) {
        System.out.println("update a +"+msg);
    }

    @Override
    public User load(int id) {
        System.out.println("load  a message,id= "+ id);
        return null;
    }

    @Override
    public void delete(int id) {
        System.out.println("delete a message id= "+id);
    }
}
