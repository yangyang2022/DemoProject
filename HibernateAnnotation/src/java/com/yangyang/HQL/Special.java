package com.yangyang.HQL;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "t_special")
public class Special {
    private int id;
    private String name;
    private String type;
    private Set<ClassRoom> classRooms;

    public Special() {
    }

    public Special(int id) {
        this.id = id;
    }

    public Special(String name, String type) {
        this.name = name;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(mappedBy = "speacil")
    @LazyCollection(LazyCollectionOption.EXTRA)
    public Set<ClassRoom> getClassRooms() {
        return classRooms;
    }

    public void setClassRooms(Set<ClassRoom> classRooms) {
        this.classRooms = classRooms;
    }
}
