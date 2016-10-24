package com.yangyang.dao.idao;

import com.yangyang.model.Message;
import com.yangyang.model.UserMessage;

public interface ImessageDao extends IBaseDao<Message> {

    //检查某个用户是否读过信件(貌似有点多余)
    boolean checkIsRead(int userId,int msgId);

    UserMessage loadUserMeassge(int userId,int msgId);
}
