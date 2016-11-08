package com.yangyang.Hongniu.DecoratorInterface;

public class FangdouGun extends WrapperGun{

    public FangdouGun(Gun gun) {
        super(gun);
    }

    @Override
    public void shoot() {
        System.out.println("add fangdou ... aim up 60% ");
        super.shoot();
    }

}
