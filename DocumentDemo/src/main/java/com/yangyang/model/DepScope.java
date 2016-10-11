package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_dep_scope")
public class DepScope {
    private Integer id;
    private int depId;
    private Department scopeDep;

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //要设置的部门id
    @Column(name = "dep_id")
    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    //可以发送的部门id
    @ManyToOne
    @JoinColumn(name = "s_dep_id")
    public Department getScopeDep() {
        return scopeDep;
    }

    public void setScopeDep(Department scopeDep) {
        this.scopeDep = scopeDep;
    }
}
