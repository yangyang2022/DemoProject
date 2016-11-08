package com.yangyang.Hongniu.DecoratorInterface;

public class DecoTest {
    public static void main(String[] args) {

        Gun ak47 = new FangdouGun(new MiaozhunGun(new SilentGun(new AK47())));
        ak47.aim();
        ak47.shoot();

        System.out.println("----------------------------");

        Gun b41 = new MiaozhunGun(new B41());
        b41.aim();

    }
}
