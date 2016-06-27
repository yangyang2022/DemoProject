package com.yangyang.model;

import java.util.Date;

public class Message {
    private int id;
    private String title;
    private String content;
    private Date createDate;

    public Message() {
    }

    public Message(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
