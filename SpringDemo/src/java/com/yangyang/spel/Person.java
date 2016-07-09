package com.yangyang.spel;

public class Person {
    private String name;
    private int age;
    private Car car;

    //city 引用Address 的city 属性
    private String city;

    //根据car的price 确定信息, price >= 30_0000 金领 否则 是白领
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                ", city='" + city + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
