package com.yangyang.service.iservice;

import com.yangyang.dto.AttachDto;
import com.yangyang.model.Attachment;
import com.yangyang.model.Message;
import com.yangyang.model.Pager;

import java.io.IOException;
import java.util.List;

public interface IMessageService {

    //添加私人信件
    void add(Message msg, Integer[] userIds, AttachDto attachDto) throws IOException;

    //删除接收到的私人信件(直接删除)
    void deleteRecive(int msgId);

    //删除发送的私人信件(首先删除有接受关系的信件)
    void deleteSend(int msgId);

    //Message load(int id); //简单load
    Message updateRead(int id,int isRead); // load 修改 isread

    //通过标题或者内容 获取信件
    Pager<Message> findSendMsg(String con);

    //获取接收的信件,isread 是否已读,0(未读) 1已读
    Pager<Message> findReceive(String con, int isRead);

    //获取私人信件的附件信息
    List<Attachment> listAttachByMsg(int msg);
}
