package com.yangyang.model;

import javax.persistence.*;

/**
 * 一个 经理 只能管理一个部门,一个部门也只有一个经理
 */
@Entity
@Table(name = "t_manager")
public class Manager {
    private int id;
    private String name;
    private Department department;

    public Manager() {
    }

    public Manager(String name) {
        this.name = name;
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

    @OneToOne(mappedBy = "manager")
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
