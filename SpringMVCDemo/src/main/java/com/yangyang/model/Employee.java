package com.yangyang.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 *  1: 使用JSR303验证
 *  2: 使用hibernate validate 验证
 *  3: 在springmvc配置文件 加入 <mvc:annotation-driven/>
 *  4: 需要在bean的对应数学系上加上注解
 *  5: 在目标方法bean类型的前面加上@Valid 注解
 */
public class Employee {
    private Integer id;

    @NotEmpty
    private String lastName;
    @NotEmpty@Email
    private String email;

    private String gender;
    private Department department;

    @NumberFormat(pattern = "#,###,###.#") //# 表示数字
    private Float salary;

    @Past //今天时间的之前
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date born;

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, String gender, Department department) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
    }

    public Employee(Integer id, String lastName, String email, String gender, Department department, Date born) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.born = born;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

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
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                ", born=" + born +
                '}';
    }
}
