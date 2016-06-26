package com.yangyang.test;

public class Person {
    private int age;
    private String name;
    private String sex;
    private String idcard;

    public Person(int age, String name, String sex, String idcard) {
        this.age = age;
        this.name = name;
        this.sex = sex;
        this.idcard = idcard;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    @Override
    public String toString() {
        return "Person{" +
                "idcard='" + idcard + '\'' +
                ", sex='" + sex + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
