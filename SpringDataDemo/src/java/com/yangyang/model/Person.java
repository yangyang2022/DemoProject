package com.yangyang.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_person")
public class Person {
    private Integer id;
    private String lastname;
    private String email;
    private Date born;

    private Address address;

    public Person() {
    }

    public Person(String lastname, String email, Date born) {
        this.lastname = lastname;
        this.email = email;
        this.born = born;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "last_name")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.DATE)
    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    @JoinColumn(name = "address_id")
    @ManyToOne
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", born=" + born +
                '}';
    }
}
