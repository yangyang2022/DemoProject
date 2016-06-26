package com.yangyang.mode;

import java.util.Date;

public class Comment {
    private int id;
    private String content;
    private Date postDate;
    private int userId;
    private int msgId;

    public Comment() {
    }

    public Comment(String content, Date postDate, int userId, int msgId) {
        this.content = content;
        this.postDate = postDate;
        this.userId = userId;
        this.msgId = msgId;
    }

    public Comment(String content, Date postDate) {
        this.content = content;
        this.postDate = postDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", postDate=" + postDate +
                ", userId=" + userId +
                ", msgId=" + msgId +
                '}';
    }
}
