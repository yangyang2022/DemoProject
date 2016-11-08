package com.yangyang.Hongniu.DecoratorInterface;

public class SilentGun extends WrapperGun{

    public SilentGun(Gun gun) {
        super(gun);
    }

    @Override
    public void shoot() {
        System.out.println("add silent device ...");
        super.shoot();
    }

}
