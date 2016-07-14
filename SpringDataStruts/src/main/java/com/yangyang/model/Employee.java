package com.yangyang.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_employee")
public class Employee {
    private Integer id;

    //不能被修改
    private String lastname;
    private String email;

    //从前端传入的string 注意转换
    private LocalDate born;
    private LocalDateTime createTime;

    private Department department;

    public Employee() {
    }

    public Employee(String lastname, String email, LocalDate born, LocalDateTime createTime, Department department) {
        this.lastname = lastname;
        this.email = email;
        this.born = born;
        this.createTime = createTime;
        this.department = department;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    @JoinColumn(name = "dept_id")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", born=" + born +
                ", createTime=" + createTime +
                ", department=" + department +
                '}';
    }
}
