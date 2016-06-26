package com.yangyang.Test;

import com.yangyang.dao.IMessageDao;
import com.yangyang.dao.MessageDao;
import com.yangyang.mode.Message;
import com.yangyang.mode.Pager;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class TsetMsgDao {
    private IMessageDao messageDao;

    @Before
    public void setUp() {
        messageDao = new MessageDao();
    }
    @Test
    public void testMsgAdd() {
        Message msg = new Message("testMsg","hello world",new Date(),100);
        messageDao.add(msg,100);
    }

    @Test
    public void testmsgDelete() {
        messageDao.delete(19);
    }

    @Test
    public void testMsgLoad() {
        System.out.println(messageDao.load(18));
    }

    @Test
    public void testMsgList() {
        (messageDao.list()).getDatas().forEach(System.out::println);
        Pager<Message> papers = messageDao.list();
        System.out.println("size: "+papers.getTotalRecord());
        System.out.println("size: "+papers.getTotalPage());
    }
}
