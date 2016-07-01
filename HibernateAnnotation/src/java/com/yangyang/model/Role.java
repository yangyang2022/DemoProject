package com.yangyang.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "t_role")
public class Role {
    private int id;
    private String name;
    private Set<Admin> admins;

    public Role() {
        admins = new HashSet<>();
    }
    public void addAmin(Admin admin){
        admins.add(admin);
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

    @ManyToMany
    @JoinTable(name = "t_role_admin",joinColumns = {@JoinColumn(name = "r_id")},
    inverseJoinColumns = {@JoinColumn(name = "a_id")} )
    public Set<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(Set<Admin> admins) {
        this.admins = admins;
    }
}
