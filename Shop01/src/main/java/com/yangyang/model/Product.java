package com.yangyang.model;

import com.yangyang.Utils.ValidateForm;
import com.yangyang.Utils.ValidateType;

public class Product {
    private int id;
    @ValidateForm(type = ValidateType.NotNull,errorMsg = "商品名称不能为空!")
    private String name;
    @ValidateForm(type = ValidateType.Number,errorMsg = "商品价格必须为数字!")
    private Double price;

    private String intro;
    private String img;
    private int status;//0 可购买,1 下架(不可购买,不显示)
    @ValidateForm(type = ValidateType.Number,errorMsg = "商品库存必须为数字!")
    private int stock;
    private Category category;

    public Product() {
    }

    public Product(String name, Double price, String intro, String img, int status, int stock, Category category) {
        this.name = name;
        this.price = price;
        this.intro = intro;
        this.img = img;
        this.status = status;
        this.stock = stock;
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
