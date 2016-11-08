package com.yangyang.DesignPattern;

interface IPrettyGirl{

    void goodLokking();
    void niceFigure();
    void greatTemperament();
}
class PrettyGirl implements IPrettyGirl{
    private String name;

    public PrettyGirl(String name) {
        this.name = name;
    }

    @Override
    public void goodLokking() {
        System.out.println(name+" --- face is goodlooking ...");
    }

    @Override
    public void niceFigure() {
        System.out.println(name+" --- figure is good!");
    }

    @Override
    public void greatTemperament() {
        System.out.println(name + " --- temperatemnet is great!");
    }
}

abstract class AbstractSearcher{
    protected IPrettyGirl prettyGirl;

    public AbstractSearcher(IPrettyGirl prettyGirl) {
        this.prettyGirl = prettyGirl;
    }
    public abstract void show();
}
class Seracher extends AbstractSearcher{

    public Seracher(IPrettyGirl prettyGirl) {
        super(prettyGirl);
    }

    @Override
    public void show() {
        System.out.println("---------------- girl info -----------------");
        super.prettyGirl.goodLokking();
        super.prettyGirl.niceFigure();
        super.prettyGirl.greatTemperament();
    }
}
public class InterfaceDemo {
    public static void main(String[] args) {

        new Seracher(new PrettyGirl("yangyang")).show();

    }
}
