package com.yangyang.model;

import javax.persistence.*;

@Entity
@Table(name = "t_group")
public class Group {
    private Integer id;
    private String groupName;

    public Group() {
    }

    public Group(Integer id) {
        this.id = id;
    }

    public Group(String groupName) {
        this.groupName = groupName;
    }

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
