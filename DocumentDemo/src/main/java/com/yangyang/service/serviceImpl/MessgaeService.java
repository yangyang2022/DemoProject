package com.yangyang.service.serviceImpl;

import com.yangyang.dao.idao.IAttachDao;
import com.yangyang.dao.idao.IUserDao;
import com.yangyang.dao.idao.ImessageDao;
import com.yangyang.dto.AttachDto;
import com.yangyang.model.*;
import com.yangyang.service.iservice.IMessageService;
import com.yangyang.systemContext.SystemContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessgaeService implements IMessageService{
    @Resource
    private ImessageDao messageDao;
    @Resource
    private IUserDao userDao;
    @Resource
    private IAttachDao attachDao;

    @Override
    public void add(Message msg, Integer[] userIds, AttachDto attachDto) throws IOException {
        //2: 设置发件人为当前登录的用户
        msg.setUser(SystemContext.getLoginUser());
        msg.setCreateDate(LocalDate.now());
        messageDao.add(msg);

        //3: 添加一组收件人
        UserMessage um = null;
        List<User> users = userDao.listByIds(userIds);
        for(User u : users){
            um = new UserMessage();
            um.setIsRead(0);
            um.setMessage(msg);
            um.setUser(u);
            messageDao.addObj(um);
        }
        //3:添加附件对象
        if(attachDto.isHasAttach()){
            File[] atts = attachDto.getAtts();
            String[] attsContemtType = attachDto.getAttsContentType();
            String[] attsFilename = attachDto.getAttsFilename();
            String[] newNames = new String[atts.length];

            for (int i = 0; i < atts.length; ++i) {
                File f = atts[i];
                String contemtType = attsContemtType[i];
                String fn = attsFilename[i];
                Attachment attachment = new Attachment();
                attachment.setContentType(contemtType);
                attachment.setCreateDate(LocalDate.now());
                attachment.setMessage(msg);
                attachment.setOldName(fn);
                String newname = getNewName(fn);
                attachment.setNewName(newname);
                newNames[i] = newname;//保存新的文件名
                attachment.setSize(f.length());
                attachment.setType(FilenameUtils.getExtension(fn));
                attachDao.add(attachment);
            }
            //上传附件
            String uploadPath = attachDto.getUploadPath();
            for (int i = 0; i < atts.length; ++i) {
                File file = atts[i];
                String name = newNames[i];
                String path = uploadPath+"/"+name;
                FileUtils.copyFile(file,new File(path));
            }
        }
    }
    private String getNewName(String name){
        String s = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)+("."+ FilenameUtils.getExtension(name));
        return s.replaceAll(":","-");
    }
    @Override
    public void deleteRecive(int msgId) {
        User user = SystemContext.getLoginUser();
        String hql = "delete UserMessage um where um.message.id=? and um.user.id=?";
        messageDao.executeByHql(hql,new Object[]{msgId,user.getId()});
    }

    @Override
    public void deleteSend(int msgId) {
        //1: 删除所有的已经接受的id
        String hql = "delete UserMessage um where um.message.id=?";
        messageDao.executeByHql(hql,msgId);
        //删除附件(数据库里删除)
        List<Attachment> atts = listAttachByMsg(msgId);
        hql = "delete Attachment att where att.message.id=?";
        messageDao.executeByHql(hql,msgId);
        //2: 删除msg对象
        messageDao.delete(msgId);
        //删除服务器上的文件
        String realPath = SystemContext.getRealPath()+"/upload";
        for(Attachment att:atts){
            File f = new File(realPath+"/"+att.getNewName());
            f.delete();
        }
    }

    @Override
    public Message updateRead(int id,int isRead) {
        if(isRead == 0){
            User user = SystemContext.getLoginUser();
            UserMessage um = messageDao.loadUserMeassge(user.getId(),id);
            if(um.getIsRead() == 0 ){
                //没有读过
                um.setIsRead(1);
                messageDao.updateObj(um);
            }
        }
        return messageDao.load(id);
    }

    @Override
    public Pager<Message> findSendMsg(String con) {
        User user = SystemContext.getLoginUser();
        String order = " order by msg.createDate desc";
        //String hql = "select msg from Message msg left join fetch msg.user user left join fetch user.department where msg.user.id=?";
        String hql = "select new Message(msg.id,msg.title,msg.content,msg.createDate) from Message msg where msg.user.id=? ";
        if(con!=null && !"".equals(con.trim())) {
            hql += " and (msg.title like ? or msg.content like ?) " + order;
            return messageDao.find(hql,new Object[]{user.getId(),"%"+con+"%","%"+con+"%"});
        }
        hql += order;
        return messageDao.find(hql,user.getId());
    }

    @Override
    public Pager<Message> findReceive(String con, int isRead) {
        String order = " order by um.message.createDate desc";
        User user = SystemContext.getLoginUser();
        String hql = "select um.message " +
                " from UserMessage um left join fetch um.message.user user left join fetch user.department " +
                " where um.isRead=? and um.user.id=?";
        if(con!=null && !"".equals(con.trim())) {
            hql += " and (um.message.title like ? or um.message.content like ?) "+order;
            return messageDao.find(hql,new Object[]{isRead,user.getId(),"%"+con+"%","%"+con+"%"});
        }
        hql += order;
        return messageDao.find(hql,new Object[]{isRead,user.getId()});
    }

    @Override
    public List<Attachment> listAttachByMsg(int msg) {
        String hql = "from Attachment att where att.message.id=?";
        return attachDao.list(hql,msg);
    }
}
