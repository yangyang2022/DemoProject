package com.yangyang.model;

import com.yangyang.PoiUtil.ExcelResource;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private Integer age;

    public User() {
    }

    public User(Integer id, String username, String password, String nickname,Integer age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.age = age;
    }

    @ExcelResource(title = "用户标识",order = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ExcelResource(title = "用户名",order = 2)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ExcelResource(title = "用户密码",order = 3)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ExcelResource(title = "用户昵称",order = 4)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @ExcelResource(title = "用户年龄",order = 5)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
