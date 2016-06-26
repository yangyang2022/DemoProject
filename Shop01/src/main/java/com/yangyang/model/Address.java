package com.yangyang.model;

import com.yangyang.Utils.ValidateForm;
import com.yangyang.Utils.ValidateType;

public class Address {
    private int id;

    @ValidateForm(type = ValidateType.NotNull,errorMsg = "地址名称不能为空!")
    private String name;

    @ValidateForm(type = ValidateType.NotNull,errorMsg = "联系电话不能为空!")
    private String phone;

    private String postcode;
    private User user; //user_id

    public Address() {
    }

    public Address(String name, String phone, String postcode) {
        this.name = name;
        this.phone = phone;
        this.postcode = postcode;
    }

    public Address(String name, String phone, String postcode, User user) {
        this.name = name;
        this.phone = phone;
        this.postcode = postcode;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", postcode='" + postcode + '\'' +
                ", user=" + user +
                '}';
    }
}
