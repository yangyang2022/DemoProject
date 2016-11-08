package com.yangyang.Hongniu.DecoratorInterface;

public class AK47 implements Gun{
    @Override
    public void aim() {
        System.out.println("AK47 aim ...");
    }

    @Override
    public void shoot() {
        System.out.println("AK47 shoot ...");
    }

    @Override
    public void load() {
        System.out.println("AK47 load ....");
    }

    @Override
    public void unload() {
        System.out.println("AK47 unload ...");
    }
}
