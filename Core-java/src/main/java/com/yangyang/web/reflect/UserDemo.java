package com.yangyang.web.reflect;

public class UserDemo {
    private int id;
    private String name;

    public static void say(String str){
        System.out.println("static hello "+str);
    }
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

    public String showw(String str){return "hello "+str;}

    public void show(String str){
        System.out.println("hello "+str);
    }
    @Override
    public String toString() {
        return "UserDemo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
