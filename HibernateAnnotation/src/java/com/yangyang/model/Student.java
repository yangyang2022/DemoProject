package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_student")
public class Student {
    private int id;
    private String name;
    private String no;
    private ClassRoom classRoom;

    public Student() {
    }

    public Student(String name, String no, ClassRoom classRoom) {
        this.name = name;
        this.no = no;
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

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cid")
    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
