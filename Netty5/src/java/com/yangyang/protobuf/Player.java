package com.yangyang.protobuf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Player implements Serializable {

    private long playerId;
    private String name;
    private int age;
    private List<Integer> skills = new ArrayList<>();

    public Player() {
    }

    public Player(long playerId, String name, int age, List<Integer> skills) {
        this.playerId = playerId;
        this.name = name;
        this.age = age;
        this.skills = skills;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Integer> getSkills() {
        return skills;
    }

    public void setSkills(List<Integer> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", skills=" + skills +
                '}';
    }
}
