package com.yangyang.model;

public class Emp {
    private int id;
    private String username;
    private String sex ;
    private double salary;
    private int depId;

    public Emp() {
    }

    public Emp(String username, String sex, double salary, int depId) {
        this.username = username;
        this.sex = sex;
        this.salary = salary;
        this.depId = depId;
    }

    public Emp(String username, String sex, double salary) {
        this.username = username;
        this.sex = sex;
        this.salary = salary;
    }

    public Emp(int id, String username, String sex, double salary, int depId) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.salary = salary;
        this.depId = depId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    @Override
    public boolean equals(Object obj) {
        Emp emp = (Emp)obj;
        return this.id == emp.id;
    }

    @Override
    public int hashCode() {
        return (9877*33)/55+id;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", salary=" + salary +
                ", depId=" + depId +
                '}';
    }
}
