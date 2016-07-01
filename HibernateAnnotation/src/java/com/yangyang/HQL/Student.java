package com.yangyang.HQL;

import javax.persistence.*;

@Entity
@Table(name = "t_stu")
public class Student {
    private int id;
    private String name;
    private String sex;
    private ClassRoom classRoom;

    public Student() {
    }

    public Student(String name, String sex, ClassRoom classRoom) {
        this.name = name;
        this.sex = sex;
        this.classRoom = classRoom;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @ManyToOne
    @JoinColumn(name = "c_id")
    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
