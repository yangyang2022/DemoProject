package com.yangyang.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_user")
public class User {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private Date born;
    private Date createDate;

    public User() {
    }

    public User(String username, String password, String nickname, Date born) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.born = born;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "create_date",columnDefinition = "timestamp")
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", born=" + born +
                '}';
    }
}
