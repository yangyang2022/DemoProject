package com.yangyang.web.model;

public class Address {
    private int id;
    private String name;
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
