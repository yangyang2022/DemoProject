package com.yangyang.model;

import javax.persistence.*;

/**
 * 一个订单属于一个客户
 * 一个客户有多个订单 --> many-2-one
 *
 */
@Entity
@Table(name = "t_order")
public class Order {
    private int id;
    private String orderName;
    private Custom custom;

    public Order() {
    }

    public Order(String orderName) {
        this.orderName = orderName;
    }

    public Order(String orderName, Custom custom) {
        this.orderName = orderName;
        this.custom = custom;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "order_name")
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOM_ID") //映射外键
    public Custom getCustom() {
        return custom;
    }

    public void setCustom(Custom custom) {
        this.custom = custom;
    }
}
