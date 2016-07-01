package com.yangyang.model;

public class IdCard {
    private int id;
    private String no;
    private Person person;

    public IdCard() {
    }

    public IdCard(String no, Person person) {
        this.no = no;
        this.person = person;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
