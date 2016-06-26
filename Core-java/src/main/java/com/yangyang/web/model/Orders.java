package com.yangyang.web.model;

import java.util.Date;
import java.util.List;

public class Orders {
    private int id;
    private Date buy_date;
    private Date pay_date;
    private Date confirm_date;
    private int status;
    private User user;
    private Address address;

    private List<Goods> goodses;

    public Orders() {
    }

    public Orders(Date buy_date, Date pay_date, Date confirm_date, int status, User user, Address address, List<Goods> goodses) {
        this.buy_date = buy_date;
        this.pay_date = pay_date;
        this.confirm_date = confirm_date;
        this.status = status;
        this.user = user;
        this.address = address;
        this.goodses = goodses;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(Date buy_date) {
        this.buy_date = buy_date;
    }

    public Date getPay_date() {
        return pay_date;
    }

    public void setPay_date(Date pay_date) {
        this.pay_date = pay_date;
    }

    public Date getConfirm_date() {
        return confirm_date;
    }

    public void setConfirm_date(Date confirm_date) {
        this.confirm_date = confirm_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(List<Goods> goodses) {
        this.goodses = goodses;
    }
}
