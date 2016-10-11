package com.yangyang.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "t_msg")
public class Message {
    private Integer id;
    private String title;
    private String content;
    private LocalDate createDate;
    private User user;

    public Message() {
    }

    public Message(Integer id, String title, String content, LocalDate createDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }

    public Message(Integer id, String title, String content, LocalDate createDate, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.user = user;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "content",columnDefinition = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_date")
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
