package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_department")
public class Department {
    private int id;
    private String depetName;
    private Manager manager;

    public Department() {
    }

    public Department(String depetName, Manager manager) {
        this.depetName = depetName;
        this.manager = manager;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "DEPT_NAME")
    public String getDepetName() {
        return depetName;
    }

    public void setDepetName(String depetName) {
        this.depetName = depetName;
    }

    //MGR_ID 唯一,注意one-2-one需要加入 unique
    @JoinColumn(name = "MGR_ID",unique = true)
    @OneToOne
    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
