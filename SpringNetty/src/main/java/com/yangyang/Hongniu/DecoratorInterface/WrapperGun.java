package com.yangyang.Hongniu.DecoratorInterface;

public abstract class WrapperGun implements Gun{

    private Gun gun;

    public WrapperGun(Gun gun) {
        this.gun = gun;
    }

    @Override
    public void aim() {
        gun.aim();
    }

    @Override
    public void shoot() {
        gun.shoot();
    }

    @Override
    public void load() {
        gun.load();
    }

    @Override
    public void unload() {
        gun.unload();
    }
}
