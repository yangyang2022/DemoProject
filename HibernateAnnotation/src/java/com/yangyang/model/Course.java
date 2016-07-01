package com.yangyang.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;
@Entity
@Table(name = "t_course")
public class Course {
    private int id;
    private String name;
    private Set<TeacherCourse> tcs;

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

    @OneToMany(mappedBy = "course")
    @LazyCollection(LazyCollectionOption.EXTRA)
    public Set<TeacherCourse> getTcs() {
        return tcs;
    }

    public void setTcs(Set<TeacherCourse> tcs) {
        this.tcs = tcs;
    }
}
