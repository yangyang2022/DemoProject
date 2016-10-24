package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_user")
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Department department;
    private String email;

    private int type;//用户类型 0-- 普通用户 1--管理员(部门添加/权限的分配)

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public User(Department department) {
        this.department = department;
    }

    public User(String username, String password, String nickname, Department department, String email, int type) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.department = department;
        this.email = email;
        this.type = type;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
    @JoinColumn(name = "dep_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
