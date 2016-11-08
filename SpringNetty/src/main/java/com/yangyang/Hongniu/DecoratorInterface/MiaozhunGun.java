package com.yangyang.Hongniu.DecoratorInterface;

public class MiaozhunGun extends WrapperGun {

    public MiaozhunGun(Gun gun) {
        super(gun);
    }

    @Override
    public void aim() {
        System.out.println("add miaozhun ... aim up 50% ");
        super.aim();
    }
}
