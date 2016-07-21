package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_users")
public class User {

    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Group group;

    public User() {
    }

    public User(String username, String password, String nickname, Group group) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.group = group;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @ManyToOne
    @JoinColumn(name = "group_id")
    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
