package com.yangyang.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_admin")
public class Admin {
    private int id;
    private String name;
    private Set<Role> roles;

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

    @ManyToMany(mappedBy = "admins")
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
