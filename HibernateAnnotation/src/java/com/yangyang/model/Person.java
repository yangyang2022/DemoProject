package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_person")
public class Person {
    private int id;
    private String name;
    private IdCard idCard;

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    @OneToOne(mappedBy = "person")
    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
