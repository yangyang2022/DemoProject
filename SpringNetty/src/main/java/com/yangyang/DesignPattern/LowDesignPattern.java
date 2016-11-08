package com.yangyang.DesignPattern;

import java.util.ArrayList;
import java.util.List;

class Girl{}
class GroupLeader{
    public void countGirls(List<Girl> girls){
        System.out.println("size: "+girls.size());
    }
}
class Teacher{
    public void command(GroupLeader leader){
        List<Girl> girls  =new ArrayList<>();
        for (int i = 0; i < 100; ++i) {
            girls.add(new Girl());
        }
        leader.countGirls(girls);
    }
}
public class LowDesignPattern {

    public static void main(String[] args) {
        new Teacher().command(new GroupLeader());
    }
}
