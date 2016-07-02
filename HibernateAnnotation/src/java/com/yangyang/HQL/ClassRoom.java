package com.yangyang.HQL;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_clz")//@BatchSize(size = 20)
public class ClassRoom {
    private int id;
    private String name;
    private int grade;//年级

    private Special speacil;

    private Set<Student> students;

    public ClassRoom() {
    }

    public ClassRoom(int id) {
        this.id = id;
    }

    public ClassRoom(String name, int grade, Special speacil) {
        this.name = name;
        this.grade = grade;
        this.speacil = speacil;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @OneToMany(mappedBy = "classRoom")
    @LazyCollection(LazyCollectionOption.EXTRA)
    @Fetch(FetchMode.SUBSELECT) //通过子查询
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sp_id")
    public Special getSpeacil() {
        return speacil;
    }

    public void setSpeacil(Special speacil) {
        this.speacil = speacil;
    }
}
