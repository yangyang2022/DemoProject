package com.yangyang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.dto.AttachDto;
import com.yangyang.model.Message;
import com.yangyang.model.User;
import com.yangyang.service.iservice.IMessageService;
import com.yangyang.service.iservice.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

import static com.yangyang.utils.ActionUtil.*;

@Controller
@Scope("prototype")
public class MessageAction extends ActionSupport implements ModelDriven<Message>{

    @Resource
    private IMessageService messageService;
    @Resource
    private IUserService userService;

    private Message message;
    private int isRead;
    private String con;
    private Integer[] sus;

    private File[] atts;
    private String[] attsContentType;
    private String[] attsFileName;

    public File[] getAtts() {
        return atts;
    }

    public void setAtts(File[] atts) {
        this.atts = atts;
    }

    public String[] getAttsContentType() {
        return attsContentType;
    }

    public void setAttsContentType(String[] attsContentType) {
        this.attsContentType = attsContentType;
    }

    public String[] getAttsFileName() {
        return attsFileName;
    }

    public void setAttsFileName(String[] attsFileName) {
        this.attsFileName = attsFileName;
    }

    public Integer[] getSus() {
        return sus;
    }

    public void setSus(Integer[] sus) {
        this.sus = sus;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getCon() {
        return con;
    }

    public void setCon(String con) {
        this.con = con;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String listSend(){

        setContextData("pages",messageService.findSendMsg(con));
        return SUCCESS;
    }

    public String listRecive(){

        setContextData("pages",messageService.findReceive(con,isRead));
        return SUCCESS;
    }
    public String deleteRecive(){

        messageService.deleteRecive(message.getId());
        setContextUrl(REDIRECT_MSG_RECIVE);
        return REDIRECT;
    }
    public String deleteSend(){

        messageService.deleteSend(message.getId());
        setContextUrl(REDIRECT_MSG_RECIVE);
        return REDIRECT;
    }
    public String show(){
        Message msg = messageService.updateRead(message.getId(),isRead);
        BeanUtils.copyProperties(msg,message);

        setContextData("atts",messageService.listAttachByMsg(message.getId()));
        return SUCCESS;
    }
    public String addInput(){

        User user = (User) getSessionDate("loginUser");
        setContextData("us",userService.listAllSendUser(user.getId()));

        return SUCCESS;
    }

    public void validateAdd(){
        if(sus == null || sus.length <= 0 ){
            this.addFieldError("sus","至少选择一个用户!");
        }
        if(message.getTitle().equals("") ||"".equals(message.getTitle().trim())){
            this.addFieldError("title","信件的标题不能为空!");
        }
        if(this.hasErrors()) addInput();
    }
    public String add() throws IOException {
        if(atts == null || atts.length <= 0){
            messageService.add(message,sus,new AttachDto(false));
        }else {
            //String uploadPath = "C:\\upload";//ServletActionContext.getServletContext().getRealPath("upload");
            String uploadPath = "C:\\mavenProject\\DemoProject\\DocumentDemo\\src\\main\\webapp\\upload";
            messageService.add(message,sus,new AttachDto(atts,attsContentType, attsFileName,uploadPath));
        }

        setContextUrl(REDIRECT_MSG_LISTSEND);
        return REDIRECT;
    }
    @Override
    public Message getModel() {
        if(message == null) message = new Message();
        return message;
    }
}
