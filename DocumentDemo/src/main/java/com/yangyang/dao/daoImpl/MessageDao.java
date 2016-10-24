package com.yangyang.dao.daoImpl;

import com.yangyang.dao.idao.ImessageDao;
import com.yangyang.model.Message;
import com.yangyang.model.UserMessage;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDao extends BaseDao<Message> implements ImessageDao{

    @Override
    public boolean checkIsRead(int userId, int msgId) {
        String hql = "select um.isRead from UserMessage um where um.user.id=? and um.message.id=?";
        Integer isRead = (Integer) this.queryForHql(hql,new Object[]{userId,msgId});
        if(isRead == null || isRead == 0){
            return false;
        }
        return true;
    }

    @Override
    public UserMessage loadUserMeassge(int userId, int msgId) {
        String hql = "select um from UserMessage um where um.user.id=? and um.message.id=?";
        return (UserMessage) this.queryForHql(hql,new Object[]{userId,msgId});
    }
}
