package com.yangyang.DesignPattern;


import com.yangyang.Util.SleepUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

interface IHanfeizi{
    void haveBreakfast();
    void havefun();
}

//class Hanfeizi implements IHanfeizi{
//    private boolean isHavingbreakfast = false;
//    private boolean isHavefun = false;
//
//    public void Fun(){
//        this.isHavefun = true;
//    }
//    public void Breakfast(){
//        this.isHavingbreakfast = true;
//    }
//    public boolean isHavefun() {
//        return isHavefun;
//    }
//
//    public void setHavefun(boolean havefun) {
//        isHavefun = havefun;
//    }
//
//    public boolean isHavingbreakfast() {
//        return isHavingbreakfast;
//    }
//
//    public void setHavingbreakfast(boolean havingbreakfast) {
//        isHavingbreakfast = havingbreakfast;
//    }
//
//    @Override
//    public void haveBreakfast() {
//        System.out.println("Hanfeizi: having breakfast ...");
//        isHavingbreakfast = true;
//    }
//
//    @Override
//    public void havefun() {
//        System.out.println("Hanfeizi: having fun ...");
//        isHavefun = true;
//    }
//}
//
//interface ILisi{
//    void update(String context);
//}
//class Lisi implements ILisi{
//
//    private void reportToQinshihuang(String reportContext){
//        System.out.println("Lisi report: hanfei is active ---> "+reportContext);
//    }
//    @Override
//    public void update(String context) {
//        System.out.println("Lisi report start ...");
//        this.reportToQinshihuang(context);
//        System.out.println("Lisi report end ...");
//    }
//}
//
//class Spy extends Thread{
//    private Hanfeizi hanfeizi;
//    private Lisi lisi;
//    private String type;
//    public Spy(Hanfeizi hanfeizi,Lisi lisi,String type){
//        this.hanfeizi = hanfeizi;
//        this.lisi = lisi;
//        this.type = type;
//    }
//    @Override
//    public void run() {
//        while (true){
//            if(this.type.equals("breakfast")){
//                if(this.hanfeizi.isHavingbreakfast()){
//                    this.lisi.update("hanfeizi is breakfast ...");
//                    this.hanfeizi.setHavingbreakfast(false);
//                }
//            }else {
//                if(this.type.equals("fun")){
//                    this.lisi.update("hanfeizi is fun ...");
//                    this.hanfeizi.setHavefun(false);
//                }
//            }
//        }
//    }
//}


interface Observer{
    void update(String context);
}

//
interface Obserable{
    void add(Observer observer);
    void delete(Observer observer);
    void notifyObservers(String context);
}

class Hanfeizi implements Obserable,IHanfeizi{
    private List<Observer> observers = new ArrayList<>();
    @Override
    public void haveBreakfast() {
        System.out.println("hanfeizi: begin eating breakfast ...");
        this.notifyObservers("hanfeizi is eating");
    }

    @Override
    public void havefun() {
        System.out.println("hanfeizi: begin fun ...");
        this.notifyObservers("hanfeizi is fun");
    }

    @Override
    public void add(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void delete(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String context) {
        observers.forEach(e->e.update(context));
    }
}

class Lisi implements Observer{
    @Override
    public void update(String context) {
        System.out.println("lisi getContext: "+context);
    }
}
class Wangsi implements Observer{
    @Override
    public void update(String context) {
        System.out.println("wangsi getContext: "+context);
    }
}
class Liusi implements Observer{
    @Override
    public void update(String context) {
        System.out.println("liusi getContext: "+context);
    }
}

class Hanfeizi_jdk extends Observable implements IHanfeizi{
    @Override
    public void haveBreakfast() {
        System.out.println("-----begin eating breakfast ....");
        super.setChanged();
        super.notifyObservers("obserer hanfei is eating ...");
    }

    @Override
    public void havefun() {
        System.out.println("-----begin eating fun ....");
        super.setChanged();
        super.notifyObservers("obserer hanfei is funning ...");
    }
}
class yangyang implements java.util.Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("getContext: "+arg.toString());
    }
}
class Yang2 implements java.util.Observer{
    @Override
    public void update(Observable o, Object arg) {
        System.out.println("getContext: "+arg.toString());
    }
}
public class OberverPattern {


    public static void main(String[] args) throws InterruptedException {

        //Lisi lisi = new Lisi();
        //Wangsi wangsi = new Wangsi();
        //Liusi liusi = new Liusi();
        //
        //Hanfeizi hanfeizi = new Hanfeizi();
        //hanfeizi.add(lisi);
        //hanfeizi.add(wangsi);
        //hanfeizi.add(liusi);
        //
        //hanfeizi.haveBreakfast();
        //SleepUtil.sleep3s();
        //hanfeizi.havefun();

        Hanfeizi_jdk han = new Hanfeizi_jdk();
        yangyang yangyang = new yangyang();
        Yang2 yang2 = new Yang2();

        han.addObserver(yangyang);
        han.addObserver(yang2);

        han.haveBreakfast();
        SleepUtil.sleep3s();
        han.havefun();

        han.deleteObserver(yang2);
        SleepUtil.sleep5s();
        han.havefun();

    }
}
