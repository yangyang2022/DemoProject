package com.yangyang.model;

import java.util.HashSet;
import java.util.Set;

public class ClassRoom {
    private int id;
    private String name;
    private int grade;

    private Set<Student> students;

    public ClassRoom() {
        students = new HashSet<>();
    }

    public ClassRoom(String name, int grade) {
        this.name = name;
        this.grade = grade;
        students = new HashSet<>();
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

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

    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", grade=" + grade +
                '}';
    }
}
