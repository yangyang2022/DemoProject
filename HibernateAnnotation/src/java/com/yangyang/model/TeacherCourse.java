package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_teach_course")
public class TeacherCourse {
    private int id;
    private double score;
    private Teacher teacher; //教师
    private Course course; // 课程

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @ManyToOne
    @JoinColumn(name = "t_id")
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @ManyToOne
    @JoinColumn(name = "c_id")
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
