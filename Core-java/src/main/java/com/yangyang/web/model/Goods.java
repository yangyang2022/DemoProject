package com.yangyang.web.model;

public class Goods {
    private int id;
    private String name;
    private Double price;
    private String intro;
    private String img;
    private int stock;
    private Category category;

    public Goods() {
    }

    public Goods(String name, Double price, String intro, String img, int stock) {
        this.name = name;
        this.price = price;
        this.intro = intro;
        this.img = img;
        this.stock = stock;
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
