package com.yangyang.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NamedQuery(name = "selectCus",query ="select c from Custom c where c.id = :id")
@Cacheable(true)
@Entity
@Table(name = "t_custom")
public class Custom {
    private int id;
    private String name;
    private String email;
    private int age;

    private Date createTime;
    private Date born;

    private Set<Order> orders ;//= new HashSet<>();

    public Custom() {
    }

    public Custom(String name, String email, int age, Date createTime, Date born) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.createTime = createTime;
        this.born = born;
    }

    //默认是lazy cascade = CascadeType.REMOVE是级联删除
    //@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    //@JoinColumn(name = "CUSTOM_ID")
    @OneToMany(mappedBy = "custom")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    //@Id
    //@TableGenerator(name = "ID_GENERATOR",table = "t_id_gen",
    //pkColumnName = "PK_NAME",pkColumnValue = "STUDENT_ID",valueColumnName = "PK_VALUE",
    //        allocationSize = 15
    //)
    //@GeneratedValue(strategy = GenerationType.TABLE,generator = "ID_GENERATOR")
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Temporal(TemporalType.DATE)
    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    @Transient
    public String getInfo(){
        return "name: "+name+" ,email: "+email;
    }

    @Override
    public String toString() {
        return "Custom{" +
                "born=" + born +
                ", createTime=" + createTime +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
