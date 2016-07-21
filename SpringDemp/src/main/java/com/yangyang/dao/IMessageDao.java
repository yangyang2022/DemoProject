package com.yangyang.model;

import com.yangyang.model.Message;
import com.yangyang.model.User;

public interface IMessageDao {
    void add(Message msg);
    void update(Message msg);
    User load(int id);
    void delete(int id);
}
