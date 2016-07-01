package com.yangyang.HQL;

import java.util.Set;

public class ClassRoom {
    private int id;
    private String name;
    private int grade;//年级

    private Speacil speacil;

    private Set<Student> students;

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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Speacil getSpeacil() {
        return speacil;
    }

    public void setSpeacil(Speacil speacil) {
        this.speacil = speacil;
    }
}
