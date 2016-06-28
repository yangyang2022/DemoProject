package com.yangyang.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.yangyang.dao.MsgDao;
import com.yangyang.model.Message;
import com.yangyang.model.PointDemo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class MessageAction extends ActionSupport implements ModelDriven<Message>{
    private static final String SUCCESS = "success";

    //createDate -- yyyy-MM-dd
    //endDate yyyy/MM/dd
    private Message message;
    private PointDemo point;

    private File photo;
    /**
     * 获取文件名 和 文件格式,名称是固定的
     * FileUtils.copyFile
     *
     */
    private String photoFileName;
    private String photoContentType;

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public String getPhotoContentType() {
        return photoContentType;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }

    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public PointDemo getPoint() {
        return point;
    }

    public void setPoint(PointDemo point) {
        this.point = point;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public String addInput(){

        return SUCCESS;
    }
    public String updateInput(){
        MsgDao msgDao = new MsgDao();

        Message tmsg  = msgDao.load();
        //message.setId(tmsg.getId());
        //message.setContent(tmsg.getContent());
        //message.setTitle(tmsg.getTitle());
        try {
            BeanUtils.copyProperties(message,tmsg);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     *在执行 add 之前都会执行 validateAdd 方法,这是服务器端验证
     */
    public String add(){
        return SUCCESS;
    }
    public void validateAdd(){
        if(message.getId() < 0 ){
            this.addFieldError("id","标识必须大于0");
        }
        if(message.getTitle() == null || "".equals(message.getTitle().trim())){
            this.addFieldError("title","标题不能为空!");
        }
    }
    public String fileInput(){
        return SUCCESS;
    }
    public String file(){
        try {
            System.out.println("fileName: "+photoFileName);
            System.out.println("fileContentType: "+photoContentType);
            FileUtils.copyFile(photo,new File("D:\\web\\upload\\"+photoFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SUCCESS;
    }
    @Override
    public Message getModel() {
        if(message == null ) message = new Message();
        return message;
    }
}
