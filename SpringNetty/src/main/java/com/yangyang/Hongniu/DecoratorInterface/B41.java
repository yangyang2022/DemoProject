package com.yangyang.Hongniu.DecoratorInterface;

public class B41 implements Gun{
    @Override
    public void aim() {
        System.out.println("B41 aim ...");
    }

    @Override
    public void shoot() {
        System.out.println("B41 shoot ...");
    }

    @Override
    public void load() {
        System.out.println("B41 load ....");
    }

    @Override
    public void unload() {
        System.out.println("B41 unload ...");
    }
}
