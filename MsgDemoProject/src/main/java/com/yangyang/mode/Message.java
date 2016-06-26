package com.yangyang.mode;

import java.util.Date;

public class Message {

    private int id;
    private String title;
    private String content;
    private Date postTime;
    private int userId;

    public Message() {
    }

    public Message(String title, String content, Date postTime) {
        this.title = title;
        this.content = content;
        this.postTime = postTime;
    }

    public Message(String title, String content, Date postTime, int userId) {
        this.title = title;
        this.content = content;
        this.postTime = postTime;
        this.userId = userId;
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

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", postTime=" + postTime +
                ", userId=" + userId +
                '}';
    }
}
