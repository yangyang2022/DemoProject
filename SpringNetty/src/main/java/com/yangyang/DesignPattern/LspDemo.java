package com.yangyang.DesignPattern;

abstract class AbstractGun{
    public abstract void shoot();
}
class Handgun extends AbstractGun{
    @Override
    public void shoot() {
        System.out.println("handgun shooting ....");
    }
}
class Rifle extends AbstractGun{
    @Override
    public void shoot() {
        System.out.println("rifle shooting ....");
    }
}
class MachineGun extends AbstractGun{
    @Override
    public void shoot() {
        System.out.println("machine shooting ....");
    }
}
class Soldier{

    private AbstractGun gun;

    public Soldier(AbstractGun gun) {
        this.gun = gun;
    }

    public void killEnermy(){
        System.out.println("begin shooting ...");
        gun.shoot();
    }
}
public class LspDemo {

    public static void main(String[] args) {

        Soldier soldier1 = new Soldier(new Handgun());
        soldier1.killEnermy();
        Soldier soldier2 = new Soldier(new Rifle());
        soldier2.killEnermy();

        Soldier soldier3 = new Soldier(new MachineGun());
        soldier3.killEnermy();

    }
}
