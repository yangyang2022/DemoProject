package com.yangyang.model;

import com.yangyang.PoiUtil.ExcelResource;

public class Student {
    private Integer id;
    private String name;
    private String no;
    private String sex;

    public Student() {
    }

    public Student(Integer id, String name, String no, String sex) {
        this.id = id;
        this.name = name;
        this.no = no;
        this.sex = sex;
    }

    @ExcelResource(title = "学生标识",order = 1)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ExcelResource(title = "学生姓名",order = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ExcelResource(title = "学生学号",order = 3)
    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @ExcelResource(title = "学生性别",order = 4)
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", no='" + no + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
