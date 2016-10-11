package com.yangyang.designParttern;

interface Choice{
    void myChioce(String s1,String s2);
}
class FirstChoice implements Choice{
    @Override
    public void myChioce(String s1, String s2) {
        System.out.println("firstChoice: "+(Integer.parseInt(s1)+Integer.parseInt(s2)));
    }
}
class SecondChocie implements Choice{
    @Override
    public void myChioce(String s1, String s2) {
        System.out.println("second: "+(Integer.parseInt(s1)*Integer.parseInt(s2)));
    }
}

class Context{
    private Choice c;
    public void setChoice(Choice cc){c = cc;}
    public void showChioce(String s1,String s2){c.myChioce(s1,s2);}
}

public class Strategy {

    public static void main(String[] args) {

        Context ctx = new Context();
        ctx.setChoice(new FirstChoice());
        ctx.showChioce("2","3");

        ctx.setChoice(new SecondChocie());
        ctx.showChioce("2","3");

        ctx.setChoice(((s1, s2) -> System.out.println("three: "+Integer.parseInt(s1) * Integer.parseInt(s2))));
        ctx.showChioce("10","20");

    }
}
