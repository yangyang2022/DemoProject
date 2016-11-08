package com.yangyang.model;

public class Person implements Cloneable{

    private Integer id;
    private String name;
    private Integer age;

    public Person() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Constructor called ...");
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Person clonePerson() throws CloneNotSupportedException {
        return (Person) this.clone();
    }
    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
