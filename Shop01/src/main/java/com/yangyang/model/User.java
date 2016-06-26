package com.yangyang.model;

import com.yangyang.Utils.ValidateForm;
import com.yangyang.Utils.ValidateType;

import java.util.List;

public class User {
    private int id;

    @ValidateForm(type = ValidateType.NotNull,errorMsg = "用户名不能为空!")
    private String username;

    @ValidateForm(type = ValidateType.Length,value = "6",errorMsg = "用户密码不能小于6!")
    private String password;

    @ValidateForm(type = ValidateType.NotNull,errorMsg = "用户昵称不能为空!")
    private String nickname;

    private int type;

    private List<Address> addresses;

    public User() {
    }

    public User(String username, String password, String nickname, int type) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.type = type;
    }

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", type=" + type +
                '}';
    }
}
