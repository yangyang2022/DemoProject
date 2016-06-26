package com.yangyang.model;

public class Dep {
    private int id;
    private String depName;

    public Dep() {
    }

    public Dep(String depName) {
        this.depName = depName;
    }

    public Dep(int id, String depName) {
        this.id = id;
        this.depName = depName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }

    @Override
    public boolean equals(Object obj) {
        Dep dep = (Dep) obj;
        return this.id == dep.id;
    }

    @Override
    public int hashCode() {
        return (9877*33)/55+id;
    }

    @Override
    public String toString() {
        //return "Dep{" +
        //        "id=" + id +
        //        ", depName='" + depName + '\'' +
        //        '}';
        return depName +" ("+id+")";
    }
}
